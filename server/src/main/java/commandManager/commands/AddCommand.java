package commandManager.commands;

import databaseLogic.databaseElementLogic.DBIntegrationUtility;
import models.Route;
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
public class AddCommand implements BaseCommand, ArgumentConsumer<Route>, AuthorizableCommand {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.add");
    private CommandStatusResponse response;
    private Route obj;
    private long callerID;

    @Override
    public void setObj(Route obj) {
        this.obj = obj;
        obj.setCreationDate(Date.from(Instant.now()));
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescr() {
        return "Adds new element to collection. It also attach created element with user " +
                "who created it";
    }

    @Override
    public String getArgs() {
        return "{element}";
    }

    @Override
    public void execute(String[] args) {
        response = DBIntegrationUtility.getInstance().addRouteToDBAndCollection(obj, callerID).toCommandResponse();
        logger.info(response.getResponse());
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }

    @Override
    public void setCallerID(long id) {
        this.callerID = id;
    }
}
