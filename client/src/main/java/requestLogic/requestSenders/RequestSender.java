package requestLogic.requestSenders;

import core.providers.ProviderRuleSet;
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
    private ProviderRuleSet[] ruleSets;

    @SafeVarargs
    public final void sendRequest(BaseRequest request, ServerConnection connection, ApplicationResponseProvider<BaseResponse>... providers)
            throws IOException {
        this.providers = providers;
        this.connection = connection;
        if (ruleSets == null)
            ruleSets = new ProviderRuleSet[0];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(request);
        logger.info("Request sent");
        connection.addResponseListeners(this);
        connection.sendData(bos.toByteArray());
    }

    @SafeVarargs
    public final void sendRequest(BaseRequest request, ServerConnection connection, ProviderRuleSet[] ruleSets, ApplicationResponseProvider<BaseResponse>... providers)
            throws IOException {
        this.ruleSets = ruleSets;
        this.sendRequest(request, connection, providers);
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
        } catch (ClassNotFoundException | ProceedException | IOException e) {
            Arrays.stream(providers).forEach(x -> x.acceptException(e));
        } catch (GotAnErrorResponseException e) {
            if (Arrays.asList(ruleSets).contains(ProviderRuleSet.UNSUBSCRIBE_ON_ERROR_RESPONSE))
                connection.removeResponseListeners(this);
            Arrays.stream(providers).forEach(x -> x.acceptException(e));
        }
    }

    @Override
    public void acceptException(Exception e) {
        Arrays.stream(providers).forEach(x -> x.acceptException(e));
        if (Arrays.asList(ruleSets).contains(ProviderRuleSet.UNSUBSCRIBE_ON_EXCEPTION))
            connection.removeResponseListeners(this);
    }
}
