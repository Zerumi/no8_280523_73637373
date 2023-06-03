package requestLogic.requestSenders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.BaseRequest;
import responseLogic.ApplicationResponseProvider;
import responses.AuthorizeResponse;
import responses.BaseResponse;
import serverLogic.ServerConnectionHandler;

import java.io.IOException;
import java.util.Arrays;

public class LoginRequestSender implements ApplicationResponseProvider<BaseResponse> {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab7");

    private ApplicationResponseProvider<AuthorizeResponse>[] providers;

    // костылииииии.............
    // upd. since 4.0: где?
    @SafeVarargs
    public final void sendLoginRequest(BaseRequest request, ApplicationResponseProvider<AuthorizeResponse>... providers) {
        this.providers = providers;
        try {
            new RequestSender().sendRequest(request, ServerConnectionHandler.getCurrentConnection(), this);
        } catch (IOException e) {
            logger.error("Something went wrong during I/O ", e);
        }
    }

    @Override
    public void acceptException(Exception e) {
        Arrays.stream(providers).forEach(x -> x.acceptException(e));
    }

    @Override
    public void acceptResponse(BaseResponse response) {
        var acceptedResponse = (AuthorizeResponse) response;
        Arrays.stream(providers).forEach(x -> x.acceptResponse(acceptedResponse));
    }
}
