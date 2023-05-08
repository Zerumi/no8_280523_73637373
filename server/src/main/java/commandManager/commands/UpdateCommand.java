package commandManager.commands;

import databaseLogic.databaseElementLogic.DBIntegrationUtility;
import models.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

import java.time.Instant;
import java.util.Date;

/**
 * Updates element by its ID.
 *
 * @author Zerumi
 * @since 1.0
 */
public class UpdateCommand implements BaseCommand, ArgumentConsumer<Route>, AuthorizableCommand {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.update");
    private CommandStatusResponse response;
    private Route obj;
    private long callerID;

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescr() {
        return "Updates element by it ID.";
    }

    @Override
    public String getArgs() {
        return "id {element}";
    }

    @Override
    public void execute(String[] args) {
        response = DBIntegrationUtility.getInstance().updateElementInDBAndCollection(obj, Long.parseLong(args[1]), callerID).toCommandResponse();
        logger.info(response.getResponse());
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }

    @Override
    public void setObj(Route obj) {
        this.obj = obj;
        obj.setCreationDate(Date.from(Instant.now()));
    }

    @Override
    public void setCallerID(long id) {
        this.callerID = id;
    }
}
