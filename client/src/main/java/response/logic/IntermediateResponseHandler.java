package response.logic;

import core.provider.ExceptionProvider;
import exception.GotAnErrorResponseException;
import exception.ProceedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import response.BaseResponse;
import response.ByteArrayPacketResponse;
import response.IntermediateResponse;
import server.logic.ServerConnectionHandler;
import server.logic.abstrct.LargePacketProvider;
import server.logic.abstrct.ListenLoopListener;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;

public class IntermediateResponseHandler implements ListenLoopListener, LargePacketProvider {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab8");
    private static final HashMap<Integer, BaseResponse> proceededResponses;

    static {
        proceededResponses = new HashMap<>();
    }

    private final ByteBuffer buffer;
    private ExceptionProvider[] providers;
    private boolean responseProceed;
    private final int responseKey;
    private final int totalDeliveryCount;
    private int current = 0;

    public IntermediateResponseHandler(IntermediateResponse response) {
        this.responseKey = response.getResponseKey();
        this.totalDeliveryCount = response.getTotalDeliveries();
        this.buffer = ByteBuffer.allocate(totalDeliveryCount * 5000);
    }


    public synchronized final BaseResponse proceedIntermediateResponse
            (ExceptionProvider... providers)
            throws ProceedException, GotAnErrorResponseException, IOException, ClassNotFoundException {

        this.providers = providers;

        if (proceededResponses.containsKey(responseKey)) {
            return proceededResponses.get(responseKey);
        }

        ServerConnectionHandler
                .getCurrentConnection()
                .addLoopListener(this);

        ServerConnectionHandler
                .getCurrentConnection()
                .getLargeResponseHandler()
                .readLargeObject(totalDeliveryCount, this);


        while (!responseProceed) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        var result = new ResponseReader().readObject(new ByteArrayInputStream(buffer.array()));
        proceededResponses.put(responseKey, result);
        return result;
    }

    @Override
    public synchronized void acceptException(Exception e) {
        Arrays.stream(providers).forEach(x -> x.acceptException(e));
    }

    @Override
    public synchronized void acceptResponse(byte[] responseArray) {
        BaseResponse response;
        try {
            response = new ResponseReader().readObject(new ByteArrayInputStream(responseArray));

            if (!response.getClass().equals(ByteArrayPacketResponse.class))
                throw new ProceedException("Got unexpected object");
            ByteArrayPacketResponse bpr = (ByteArrayPacketResponse) response;
            if (bpr.getResponseKey() != responseKey) {
                return;
                // not our packet
            }
            if (bpr.getDeliveryNumber() != current++)
                throw new ProceedException("We have lost some packets... expected: " + current + " / got: "
                        + bpr.getDeliveryNumber());
            buffer.put(((ByteArrayPacketResponse) response).getPacket());
        } catch (IOException | ClassNotFoundException | GotAnErrorResponseException | ProceedException e) {
            Arrays.stream(providers).forEach((x) -> x.acceptException(e));
        }
    }

    @Override
    public synchronized void acceptReadingOver() {
        responseProceed = true;
        notifyAll();
    }

    @Override
    public void loopEndAction() {
        logger.info("removed response");
        proceededResponses.remove(responseKey);
        ServerConnectionHandler
                .getCurrentConnection()
                .removeLoopListener(this);
    }
}