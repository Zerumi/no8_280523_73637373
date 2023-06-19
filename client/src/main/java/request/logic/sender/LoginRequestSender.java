package request.logic.sender;

import core.provider.ProviderRuleSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.BaseRequest;
import response.AuthorizeResponse;
import response.BaseResponse;
import response.logic.ApplicationResponseProvider;
import server.logic.ServerConnectionHandler;

import java.io.IOException;
import java.util.Arrays;

public class LoginRequestSender implements ApplicationResponseProvider<BaseResponse> {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab7");

    private ApplicationResponseProvider<AuthorizeResponse>[] providers;

    private final RequestSender requestSender = new RequestSender();

    // костылииииии.............
    // upd. since 4.0: где?
    @SafeVarargs
    public final void sendLoginRequest(BaseRequest request, ApplicationResponseProvider<AuthorizeResponse>... providers) {
        this.providers = providers;
        try {
            requestSender.sendRequest(request, ServerConnectionHandler.getCurrentConnection(),
                    new ProviderRuleSet[]{ProviderRuleSet.UNSUBSCRIBE_ON_ERROR_RESPONSE,
                            ProviderRuleSet.UNSUBSCRIBE_ON_EXCEPTION},
                    this);
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
        if (response.getClass().equals(AuthorizeResponse.class)) {
            var acceptedResponse = (AuthorizeResponse) response;
            Arrays.stream(providers).forEach(x -> x.acceptResponse(acceptedResponse));
            requestSender.removeListener(this);
        }
    }
}