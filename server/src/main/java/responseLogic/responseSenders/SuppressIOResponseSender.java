package responseLogic.responseSenders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.CallerBack;
import responses.BaseResponse;
import serverLogic.ServerConnection;

import java.io.IOException;

public class SuppressIOResponseSender {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");

    public static void sendResponse(BaseResponse response, ServerConnection connection, CallerBack to) {
        try {
            ResponseSender.sendResponse(response, connection, to);
        } catch (IOException e) {
            logger.fatal("Something went wrong during I/O", e);
        }
    }
}
