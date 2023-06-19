package request.logic.sender;

import core.provider.ExceptionProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.BaseRequest;
import response.BaseResponse;
import response.logic.ApplicationResponseProvider;
import server.logic.abstrct.ServerConnection;

import java.io.IOException;
import java.util.Arrays;

public class SuppressResponseRequestSender implements ApplicationResponseProvider<BaseResponse> {

    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab8");

    private final RequestSender requestSender;
    private ExceptionProvider[] providers;

    {
        requestSender = new RequestSender();
    }

    public void sendRequestAndSuppressResponse(BaseRequest request, ServerConnection connection, ExceptionProvider... providers) {
        this.providers = providers;
        try {
            requestSender.sendRequest(request, connection, this);
        } catch (IOException e) {
            logger.error("I/O error", e);
        }
    }

    public void unsubscribeFromExceptions() {
        requestSender.removeListener(this);
    }

    @Override
    public void acceptException(Exception e) {
        Arrays.stream(providers).forEach(x -> x.acceptException(e));
    }

    @Override
    public void acceptResponse(BaseResponse response) {

    }
}
