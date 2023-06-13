package request.logic.sender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.ShowCollectionRequest;
import response.logic.ApplicationResponseProvider;
import response.BaseResponse;
import response.ShowCollectionResponse;
import server.logic.ServerConnectionHandler;

import java.io.IOException;
import java.util.Arrays;

public class ShowCollectionRequestSender implements ApplicationResponseProvider<BaseResponse> {

    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab8");

    private final RequestSender requestSender;

    {
        requestSender = new RequestSender();
    }

    private ApplicationResponseProvider<ShowCollectionResponse>[] providers;

    @SafeVarargs
    public final void sendCollectionRequest(ApplicationResponseProvider<ShowCollectionResponse>... providers) {
        this.providers = providers;
        try {
            requestSender.sendRequest
                    (new ShowCollectionRequest(), ServerConnectionHandler.getCurrentConnection(), this);
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
            requestSender.removeListener(this);
        }
    }
}
