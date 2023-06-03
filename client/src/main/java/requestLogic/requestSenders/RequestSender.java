package requestLogic.requestSenders;

import exceptions.GotAnErrorResponseException;
import exceptions.ProceedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.BaseRequest;
import responseLogic.ApplicationResponseProvider;
import responseLogic.ResponseReader;
import responses.BaseResponse;
import serverLogic.ServerConnection;
import serverLogic.ServerResponseProvider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class RequestSender implements ServerResponseProvider {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");
    private ApplicationResponseProvider<BaseResponse>[] providers;

    @SafeVarargs
    public final void sendRequest(BaseRequest request, ServerConnection connection, ApplicationResponseProvider<BaseResponse>... providers)
            throws IOException {
        this.providers = providers;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(request);
        logger.info("Request sent");
        connection.sendData(bos.toByteArray(), this);
    }

    @Override
    public void acceptResponse(InputStream response) {
        try {
            if (response != null) {
                ResponseReader reader = new ResponseReader(response);
                var reddenResponse = reader.readObject();
                Arrays.stream(providers).forEach(x -> x.acceptResponse(reddenResponse));
                logger.info("Received response");
            } else logger.info("Received null");
        } catch (ClassNotFoundException | ProceedException | GotAnErrorResponseException | IOException e) {
            Arrays.stream(providers).forEach(x -> x.acceptException(e));
        }
    }

    @Override
    public void acceptException(Exception e) {
        Arrays.stream(providers).forEach(x -> x.acceptException(e));
    }
}
