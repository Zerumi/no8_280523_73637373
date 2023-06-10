package command.manager.commands;

import database.logic.element.DBIntegrationUtility;
import models.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;
import utils.Utilities;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

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
        return "Updates element by it ID. N/B: you may only edit elements belongs to you";
    }

    @Override
    public String getArgs() {
        return "id {element}";
    }

    @Override
    public void execute(String[] args) {
        long id = Optional.ofNullable(Utilities.handleUserInputID(args[1])).orElse(-1L);
        if (id < 0) {
            response = new CommandStatusResponse("You must enter a valid ID", -7);
        }
        response = DBIntegrationUtility.getInstance().updateElementInDBAndCollection(obj, id, callerID).toCommandResponse();
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
