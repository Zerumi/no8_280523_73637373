package requestLogic.requestSenders;

import commandLogic.CommandDescription;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.ArgumentCommandClientRequest;
import responseLogic.ApplicationResponseProvider;
import responses.BaseResponse;
import responses.CommandStatusResponse;
import serverLogic.ServerConnection;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

public class ArgumentRequestSender<T extends Serializable> implements ApplicationResponseProvider<BaseResponse> {

    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");

    ApplicationResponseProvider<CommandStatusResponse>[] providers;

    @SafeVarargs
    public final void sendCommand(CommandDescription command, String[] args, T argument, ServerConnection connection, ApplicationResponseProvider<CommandStatusResponse>... providers) {
        this.providers = providers;
        try {
            ArgumentCommandClientRequest<T> rq = new ArgumentCommandClientRequest<>(command, args, argument);
            logger.info("Sending command request...");
            new RequestSender().sendRequest(rq, connection, this);
        } catch (IOException e) {
            logger.error("Something went wrong during I/O operations.", e);
        }
    }

    @Override
    public void acceptException(Exception e) {
        Arrays.stream(providers).forEach(x -> x.acceptException(e));
    }

    @Override
    public void acceptResponse(BaseResponse response) {
        var acceptedResponse = (CommandStatusResponse) response;
        Arrays.stream(providers).forEach(x -> x.acceptResponse(acceptedResponse));
    }
}
