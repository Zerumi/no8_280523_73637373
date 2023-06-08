package serverLogic.udp;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import serverLogic.abstractLogic.*;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;
import java.util.concurrent.*;

public class UdpServerConnection implements ServerConnection, LargeResponseHandler {
    public static final int BUFFER_SIZE = 4096;
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");
    private final ExecutorService service;
    private final CopyOnWriteArrayList<ServerResponseProvider> providers;
    private final CopyOnWriteArrayList<ListenLoopListener> loopListeners;
    protected final DatagramChannel channel;
    protected SocketAddress address;

    {
        service = Executors.newCachedThreadPool();
        providers = new CopyOnWriteArrayList<>();
        loopListeners = new CopyOnWriteArrayList<>();
    }

    protected UdpServerConnection(DatagramChannel channel, SocketAddress address) {
        this.channel = channel;
        this.address = address;
    }


    //  Fixed // Наблюдение
    //  В начале программы отправляется запрос на авторизацию (+ Provider / - Provider)
    //  Потом отправятся запрос на подписку на события (+ Provider[0])
    //  Потом на получение коллекции (+ Provider) / ! \ Intermediate response ownership
    //  (И где-то там сверху еще слушатель висит. Итого 3 провайдера)
    //  Лишь один из запросов имеет возможность получить право на чтение Intermediate Response
    //  Но читают все запросы
    //  Оказалось, что проверка лишь слушателей не увенчалась успехом
    //  Требуются дополнительные решения

    /**
     * Method for open a connection.
     */
    @Override
    public void openConnection() throws IOException {
        if (!channel.isConnected()) {
            channel.connect(address);
            logger.log(Level.INFO, "Opened channel to: " + address);

            // open listening
            Thread thread = new Thread(() -> {
                while (true) {
                    Callable<byte[]> callable = this::listenServer;
                    Future<byte[]> bosFuture = service.submit(callable);
                    byte[] bos = null;
                    try {
                        bos = bosFuture.get();
                    } catch (InterruptedException | ExecutionException e) {
                        logger.error("Exception in server logic");
                        providers.forEach(x -> x.acceptException(e));
                    } finally {
                        if (bos != null) {
                            byte[] finalBos = bos;
                            providers.forEach(x -> x.acceptResponse(finalBos));
                            loopListeners.forEach(ListenLoopListener::loopEndAction);
                        }
                    }
                }
            });

            thread.start();
        }
    }

    /**
     * Method for close a connection
     */
    @Override
    public void closeConnection() throws IOException {
        if (channel.isConnected() && channel.isOpen()) {
            try {
                channel.disconnect();
                channel.close();
            } catch (IOException e) {
                channel.close();
            }
        }
    }

    @Override
    public void sendData(byte[] bytesToSend) throws IOException {
        if (channel.isConnected() && channel.isOpen()) {
            var buf = ByteBuffer.wrap(bytesToSend);
            channel.send(buf, address);
        } else this.openConnection();
    }

    @Override
    public void addResponseListeners(ServerResponseProvider... providers) {
        this.providers.addAll(Arrays.stream(providers).toList());
    }

    @Override
    public void removeResponseListeners(ServerResponseProvider... providers) {
        this.providers.removeAll(Arrays.stream(providers).toList());
    }

    @Override
    public LargeResponseHandler getLargeResponseHandler() {
        return this;
    }

    // Fixed // Уязвимость. Публично слушать сервер небезопасно,
    //  так как много слушателей пытаются обработать IntermediateRequest,
    //  и как следствие, ждут ответов, которые им уже никто не даст
    //  Требуется переработать систему "промежуточных" запросов
    //  Например, смотреть и ловить такие запросы прямо здесь
    //  Не считаю это лучшим решением, поэтому есть еще вариант
    //  Ограничить слушателей
    private byte[] listenServer() throws IOException {
        byte[] res = null;
        if (channel.isConnected() && channel.isOpen()) {
            ByteBuffer buf = ByteBuffer.allocate(BUFFER_SIZE);
            address = channel.receive(buf);
            logger.debug("response read");
            logger.trace("bytes: " + Arrays.toString(buf.array()));
            res = buf.array();
        } else this.openConnection();
        return res;
    }

    @Override
    public void readLargeObject(int packetsCount, LargePacketProvider... packetProviders) {
        for (int i = 0; i < packetsCount; i++) {
            Arrays.stream(packetProviders.clone()).forEach(x -> {
                try {
                    x.acceptResponse(listenServer());
                } catch (IOException e) {
                    x.acceptException(e);
                }
            });
        }
        Arrays.stream(packetProviders.clone()).forEach(LargePacketProvider::acceptReadingOver);
    }

    @Override
    public void addLoopListener(ListenLoopListener listener) {
        this.loopListeners.add(listener);
    }

    @Override
    public void removeLoopListener(ListenLoopListener listener) {
        this.loopListeners.remove(listener);
    }
}
