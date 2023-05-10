package commandManager.commands;

import databaseLogic.databaseElementLogic.DBIntegrationUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

/**
 * Removes element from collection by id.
 *
 * @author Zerumi
 * @since 1.0
 */
public class RemoveByIdCommand implements BaseCommand, AuthorizableCommand {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.rmByID");
    private CommandStatusResponse response;
    private long callerID;

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescr() {
        return "Removes element from collection by id. N/B: you may only remove elements belongs to you";
    }

    @Override
    public String getArgs() {
        return "id";
    }

    @Override
    public void execute(String[] args) {
        if (!DBIntegrationUtility.getInstance().removeFromCollectionAndDB(callerID, Long.parseLong(args[1])))
            response = new CommandStatusResponse("Element with that id doesn't exists or you don't have access to edit this object.", -922);
        else
            response = CommandStatusResponse.ofString("Executed.");

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
