package requestLogic.requestSenders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.ShowCollectionRequest;
import responseLogic.ApplicationResponseProvider;
import responses.BaseResponse;
import responses.ShowCollectionResponse;
import serverLogic.ServerConnectionHandler;

import java.io.IOException;
import java.util.Arrays;

public class ShowCollectionRequestSender implements ApplicationResponseProvider<BaseResponse> {

    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab8");

    private ApplicationResponseProvider<ShowCollectionResponse>[] providers;

    @SafeVarargs
    public final void sendCollectionRequest(ApplicationResponseProvider<ShowCollectionResponse>... providers) {
        this.providers = providers;
        try {
            new RequestSender().sendRequest(new ShowCollectionRequest(), ServerConnectionHandler.getCurrentConnection(), this);
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
        if (response.getClass().equals(ShowCollectionResponse.class)) {
            ShowCollectionResponse acceptedResponse = (ShowCollectionResponse) response;
            Arrays.stream(providers).forEach(x -> x.acceptResponse(acceptedResponse));
        }
    }
}
