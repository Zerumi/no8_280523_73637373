package request.logic.sender;

import command.logic.CommandDescription;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.ArgumentCommandClientRequest;
import request.LocalizedCommandClientRequest;
import response.logic.ApplicationResponseProvider;
import response.BaseResponse;
import response.CommandStatusResponse;
import server.logic.abstrct.ServerConnection;
import util.LocaleHolder;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Locale;

public class ArgumentRequestSender<T extends Serializable> implements ApplicationResponseProvider<BaseResponse> {

    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");

    ApplicationResponseProvider<CommandStatusResponse>[] providers;

    @SafeVarargs
    public final void sendCommand(CommandDescription command, String[] args, T argument, ServerConnection connection, ApplicationResponseProvider<CommandStatusResponse>... providers) {
        this.providers = providers;
        try {
            var rq = new LocalizedCommandClientRequest(new ArgumentCommandClientRequest<>(command, args, argument), LocaleHolder.getLocale());
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
        if (response.getClass().equals(CommandStatusResponse.class)) {
            var acceptedResponse = (CommandStatusResponse) response;
            Arrays.stream(providers).forEach(x -> x.acceptResponse(acceptedResponse));
        }
    }
}
