package listen.logic;

import exceptions.GotAnErrorResponseException;
import exceptions.ProceedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import response.logic.ApplicationResponseProvider;
import response.logic.ResponseReader;
import responses.BaseResponse;
import server.logic.ServerConnectionHandler;
import server.logic.abstrct.ServerResponseProvider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerListener<T extends BaseResponse> implements ServerResponseProvider {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");
    private final Class<T> responseToListen;
    private final ArrayList<ApplicationResponseProvider<BaseResponse>> providers = new ArrayList<>();

    public ServerListener(Class<T> responseToListen) {
        this.responseToListen = responseToListen;
    }

    public void startListen() {
        ServerConnectionHandler.getCurrentConnection().addResponseListeners(this);
    }

    public void stopListen() {
        ServerConnectionHandler.getCurrentConnection().removeResponseListeners(this);
    }

    @SafeVarargs
    public final ServerListener<T> addListeners(ApplicationResponseProvider<BaseResponse>... providers) {
        this.providers.addAll(Arrays.stream(providers).toList());
        return this;
    }

    @SafeVarargs
    public final ServerListener<T> removeListeners(ApplicationResponseProvider<BaseResponse>... providers) {
        this.providers.removeAll(Arrays.stream(providers).toList());
        return this;
    }

    @Override
    public void acceptException(Exception e) {
        providers.forEach(x -> x.acceptException(e));
    }

    @Override
    public void acceptResponse(byte[] response) {
        try {
            if (response != null) {
                ResponseReader reader = new ResponseReader();
                if (reader.resolveType(new ByteArrayInputStream(response)).equals(responseToListen)) {
                    var reddenResponse = reader.readObject(new ByteArrayInputStream(response));
                    providers.forEach(x -> x.acceptResponse(reddenResponse));
                    logger.info("[ServerListener] Received response " + responseToListen.getSimpleName());
                }
            } else logger.info("[ServerListener] Received null");
        } catch (ClassNotFoundException | ProceedException | GotAnErrorResponseException | IOException e) {
            providers.forEach(x -> x.acceptException(e));
        }
    }
}
