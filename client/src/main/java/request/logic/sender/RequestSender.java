package request.logic.sender;

import core.provider.ProviderRuleSet;
import exception.GotAnErrorResponseException;
import exception.ProceedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.BaseRequest;
import response.BaseResponse;
import response.logic.ApplicationResponseProvider;
import response.logic.ResponseReader;
import server.logic.abstrct.ServerConnection;
import server.logic.abstrct.ServerResponseProvider;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class RequestSender implements ServerResponseProvider {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");
    private final CopyOnWriteArrayList<ApplicationResponseProvider<BaseResponse>> providers;

    {
        providers = new CopyOnWriteArrayList<>();
    }

    private ServerConnection connection;
    private ProviderRuleSet[] ruleSets;

    @SafeVarargs
    public final void sendRequest(BaseRequest request, ServerConnection connection, ApplicationResponseProvider<BaseResponse>... providers)
            throws IOException {
        this.providers.addAll(Arrays.stream(providers).toList());
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

    public void removeListener(ApplicationResponseProvider<BaseResponse> provider) {
        this.providers.remove(provider);
        if (providers.size() == 0 && connection != null)
            connection.removeResponseListeners(this);
    }

    @Override
    public void acceptResponse(byte[] response) {
        try {
            if (response != null) {
                ResponseReader reader = new ResponseReader();
                var reddenResponse = reader.readObject(new ByteArrayInputStream(response));
                providers.forEach(x -> x.acceptResponse(reddenResponse));
                logger.info("Received response");
            } else logger.info("Received null");
        } catch (ClassNotFoundException | ProceedException | IOException e) {
            providers.forEach(x -> x.acceptException(e));
        } catch (GotAnErrorResponseException e) {
            if (Arrays.asList(ruleSets).contains(ProviderRuleSet.UNSUBSCRIBE_ON_ERROR_RESPONSE))
                connection.removeResponseListeners(this);
            providers.forEach(x -> x.acceptException(e));
        }
    }

    @Override
    public void acceptException(Exception e) {
        providers.forEach(x -> x.acceptException(e));
        if (Arrays.asList(ruleSets).contains(ProviderRuleSet.UNSUBSCRIBE_ON_EXCEPTION))
            connection.removeResponseListeners(this);
    }
}
