package command.logic.external.recievers;

import command.logic.CommandDescription;
import command.logic.reciever.receivers.ExternalArgumentReceiver;
import exceptions.BuildObjectException;
import models.Route;
import models.handlers.ModuleHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.logic.senders.ArgumentRequestSender;
import response.logic.ApplicationResponseProvider;
import responses.CommandStatusResponse;
import server.logic.ServerConnectionHandler;

public class ArgumentRouteCommandReceiver implements ExternalArgumentReceiver<Route>, ApplicationResponseProvider<CommandStatusResponse> {

    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab6");
    ModuleHandler<Route> handler;
    Route route;

    {
        route = new Route();
    }

    public ArgumentRouteCommandReceiver(ModuleHandler<Route> handler) {
        this.handler = handler;
    }

    @Override
    public boolean receiveCommand(CommandDescription command, String[] args) throws BuildObjectException {
        route = handler.buildObject();
        new ArgumentRequestSender<Route>().sendCommand(command, args, route, ServerConnectionHandler.getCurrentConnection(), this);
        return true; // todo: ??? return true? connect 2 logics between them
    }

    @Override
    public Route getArguemnt() {
        return route;
    }

    @Override
    public void acceptException(Exception e) {

        // todo: drop exception above (same as response)

        logger.error(e);
    }

    @Override
    public void acceptResponse(CommandStatusResponse response) {

        // todo: drop response level above

        if (response != null) {
            logger.info("Status code: " + response.getStatusCode());
            logger.info("Response: \n" + response.getResponse());
        }
    }
}
