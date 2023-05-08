package commandManager.commands;

import databaseLogic.databaseElementLogic.DBIntegrationUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

/**
 * Clears collection
 *
 * @author Zerumi
 * @since 1.0
 */
public class ClearCommand implements BaseCommand, AuthorizableCommand {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.clear");
    private CommandStatusResponse response;

    private long callerID;

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescr() {
        return "Clears collection";
    }

    @Override
    public void execute(String[] args) {
        response = DBIntegrationUtility.getInstance().clearCollectionInDBAndMemory(callerID).toCommandResponse();
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
