package request.logic.senders;

import command.logic.CommandDescription;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.CommandClientRequest;
import response.logic.ApplicationResponseProvider;
import responses.BaseResponse;
import responses.CommandStatusResponse;
import server.logic.abstrct.ServerConnection;

import java.io.IOException;
import java.util.Arrays;

public class CommandRequestSender implements ApplicationResponseProvider<BaseResponse> {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");

    private ApplicationResponseProvider<CommandStatusResponse>[] providers;

    @SafeVarargs
    public final void sendCommand(CommandDescription command, String[] args, ServerConnection connection, ApplicationResponseProvider<CommandStatusResponse>... providers) {
        this.providers = providers;
        try {
            var rq = new CommandClientRequest(command, args);
            logger.info("Sending command request...");
            new RequestSender().sendRequest(rq, connection, this);
        } catch (IOException e) {
            logger.error("Something went wrong during I/O operations", e);
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
