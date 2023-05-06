package commandManager.commands;

import databaseLogic.databaseElementLogic.DBIntegrationUtility;
import models.Route;
import models.handlers.RouteIDHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

import java.time.Instant;
import java.util.Date;

/**
 * Adds new element to collection.
 *
 * @author Zerumi
 * @since 1.0
 */
public class AddCommand implements BaseCommand, ArgumentConsumer<Route> {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.add");
    private CommandStatusResponse response;

    private Route obj;

    @Override
    public void setObj(Route obj) {
        this.obj = obj;
        obj.setId(RouteIDHandler.getInstance().getNextID());
        obj.setCreationDate(Date.from(Instant.now()));
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescr() {
        return "Adds new element to collection.";
    }

    @Override
    public String getArgs() {
        return "{element}";
    }

    @Override
    public void execute(String[] args) {
        response = DBIntegrationUtility.addRouteToDBAndCollection(obj).toCommandResponse();
        logger.info(response.getResponse());
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }
}
