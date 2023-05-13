package responseLogic.responseSenders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.CallerBack;
import responses.BaseResponse;
import serverLogic.ServerConnection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ResponseSender {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");
    private static final Executor sendRsMtExecutor = Executors.newWorkStealingPool();

    public static void sendResponse(BaseResponse response, ServerConnection connection, CallerBack to) {
        sendRsMtExecutor.execute(() -> {
            if (response != null) {
                sendResponseLogic(response, connection, to);
            }
        });
    }

    protected static void sendSyncResponse(BaseResponse response, ServerConnection connection, CallerBack to) {
        sendResponseLogic(response, connection, to);
    }

    private static void sendResponseLogic(BaseResponse response, ServerConnection connection, CallerBack to) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos;
            oos = new ObjectOutputStream(bos);
            oos.writeObject(response);
            if (bos.size() > 4096) {
                new IntermediateResponseSender().sendLargeResponse(bos, to, connection);
            } else
                connection.sendData(bos.toByteArray(), to.getAddress(), to.getPort());
            logger.info("response sent.");
        } catch (IOException e) {
            logger.error("Something went wrong during I/O", e);
        }
    }
}
