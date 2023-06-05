package requestLogic.requestSenders;

import exceptions.GotAnErrorResponseException;
import exceptions.ProceedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.BaseRequest;
import responseLogic.ApplicationResponseProvider;
import responseLogic.ResponseReader;
import responses.BaseResponse;
import serverLogic.abstractLogic.ServerConnection;
import serverLogic.abstractLogic.ServerResponseProvider;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class RequestSender implements ServerResponseProvider {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");
    private ApplicationResponseProvider<BaseResponse>[] providers;
    private ServerConnection connection;

    @SafeVarargs
    public final void sendRequest(BaseRequest request, ServerConnection connection, ApplicationResponseProvider<BaseResponse>... providers)
            throws IOException {
        this.providers = providers;
        this.connection = connection;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(request);
        logger.info("Request sent");
        connection.addResponseListeners(this);
        connection.sendData(bos.toByteArray());
    }

    @Override
    public void acceptResponse(byte[] response) {
        try {
            if (response != null) {
                ResponseReader reader = new ResponseReader();
                var reddenResponse = reader.readObject(new ByteArrayInputStream(response));
                Arrays.stream(providers).forEach(x -> x.acceptResponse(reddenResponse));
                connection.removeResponseListeners(this);
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
