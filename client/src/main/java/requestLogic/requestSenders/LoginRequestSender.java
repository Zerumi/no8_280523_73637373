package requestLogic.requestSenders;

import exceptions.GotAnErrorResponseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.BaseRequest;
import responses.AuthorizeResponse;
import serverLogic.ServerConnectionHandler;

import java.io.IOException;

public class LoginRequestSender {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab7");

    // костылииииии.............
    public AuthorizeResponse sendLoginRequest(BaseRequest request) {
        AuthorizeResponse response = null;
        try {
            response = (AuthorizeResponse) new RequestSender().sendRequest(request, ServerConnectionHandler.getCurrentConnection());
        } catch (IOException e) {
            logger.error("Something went wrong during I/O ", e);
        } catch (GotAnErrorResponseException e) {
            logger.error("Received error from a server! " + e.getErrorResponse().getMsg());
        }
        return response;
    }
}
