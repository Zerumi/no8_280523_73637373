package command.logic.external.recievers;

import command.logic.CommandDescription;
import command.logic.reciever.receivers.ExternalBaseReceiver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.logic.senders.CommandRequestSender;
import response.logic.ApplicationResponseProvider;
import responses.CommandStatusResponse;
import server.logic.ServerConnectionHandler;

import java.util.Arrays;

public class NonArgumentReceiver implements ExternalBaseReceiver, ApplicationResponseProvider<CommandStatusResponse> {

    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab6");
    private final ApplicationResponseProvider<CommandStatusResponse>[] providers;

    @SafeVarargs
    public NonArgumentReceiver(ApplicationResponseProvider<CommandStatusResponse>... providers) {
        this.providers = providers;
    }

    @Override
    public boolean receiveCommand(CommandDescription command, String[] args) {
        new CommandRequestSender().sendCommand(command, args, ServerConnectionHandler.getCurrentConnection(), this);
        return true; // todo: ??? same problem
    }

    @Override
    public void acceptException(Exception e) {

        Arrays.stream(providers).forEach(x -> x.acceptException(e));

        logger.error(e);
    }

    @Override
    public void acceptResponse(CommandStatusResponse response) {

        Arrays.stream(providers).forEach(x -> x.acceptResponse(response));

        if (response != null) {
            logger.info("Status code: " + response.getStatusCode());
            logger.info("Response: \n" + response.getResponse());
        }
    }
}
