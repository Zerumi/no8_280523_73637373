package request.logic.sender;

import core.provider.ExceptionProvider;
import exception.GotAnErrorResponseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.RemoveFromCollectionRequest;
import response.BaseResponse;
import response.CollectionUpdatedResponse;
import response.logic.ApplicationResponseProvider;
import server.logic.ServerConnectionHandler;

import java.io.IOException;
import java.util.Arrays;

public class RemoveFromCollectionRequestSender implements ApplicationResponseProvider<BaseResponse> {

    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab7");

    private final RequestSender requestSender = new RequestSender();

    private ExceptionProvider[] providers;

    public void sendRemoveRequest(long elementID, ExceptionProvider... providers) {
        this.providers = providers;

        try {
            requestSender.sendRequest(new RemoveFromCollectionRequest(elementID),
                    ServerConnectionHandler.getCurrentConnection(), this);
        } catch (IOException e) {
            logger.error("Something went wrong during I/O ", e);
        }
    }

    @Override
    public void acceptException(Exception e) {
        if (e instanceof GotAnErrorResponseException) {
            Arrays.stream(providers).forEach(x -> x.acceptException(e));
            requestSender.removeListener(this);
        }
    }

    @Override
    public void acceptResponse(BaseResponse response) {
        if (response instanceof CollectionUpdatedResponse) {
            requestSender.removeListener(this);
        }
    }
}
