package command.logic.external.reciever;

import command.logic.CommandDescription;
import command.logic.reciever.receiver.ExternalArgumentReceiver;
import exception.BuildObjectException;
import model.Route;
import model.handler.ModuleHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.logic.sender.ArgumentRequestSender;
import response.CommandStatusResponse;
import response.logic.ApplicationResponseProvider;
import server.logic.ServerConnectionHandler;

import java.util.Arrays;

public class ArgumentRouteCommandReceiver implements ExternalArgumentReceiver<Route>, ApplicationResponseProvider<CommandStatusResponse> {
    private boolean flag = false;
    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab6");
    private final ApplicationResponseProvider<CommandStatusResponse>[] providers;
    private final ModuleHandler<Route> handler;
    private Route route;

    {
        route = new Route();
    }

    @SafeVarargs
    public ArgumentRouteCommandReceiver
            (ModuleHandler<Route> handler, ApplicationResponseProvider<CommandStatusResponse>... providers) {
        this.providers = providers;
        this.handler = handler;
    }

    @Override
    public synchronized boolean receiveCommand(CommandDescription command, String[] args) throws BuildObjectException {
        route = handler.buildObject();
        new ArgumentRequestSender<Route>().sendCommand(command, args, route, ServerConnectionHandler.getCurrentConnection(), this);
        try {
            while (!flag)
                wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return true;
    }

    @Override
    public synchronized Route getArguemnt() {
        return route;
    }

    @Override
    public synchronized void acceptException(Exception e) {

        Arrays.stream(providers).forEach(x -> x.acceptException(e));

        logger.error(e);

        this.flag = true;
        notifyAll();
    }

    @Override
    public synchronized void acceptResponse(CommandStatusResponse response) {

        Arrays.stream(providers).forEach(x -> x.acceptResponse(response));

        if (response != null) {
            logger.info("Status code: " + response.getStatusCode());
            logger.info("Response: \n" + response.getResponse());
        }

        this.flag = true;
        notifyAll();
    }
}
