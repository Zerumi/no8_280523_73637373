package commandManager.commands;

import clientLogic.SessionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

/**
 * Terminates the application (without saving collection).
 *
 * @author Zerumi
 * @since 1.0
 */
public class ExitCommand implements BaseCommand, AuthorizableCommand {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.exit");
    private CommandStatusResponse response;
    private long callerBackID;

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescr() {
        return "Invoke a logout and terminate the application";
    }

    @Override
    public void execute(String[] args) {
        logger.trace("Invoked exit command.");
        SessionHandler.getInstance().removeSession(callerBackID);
        response = CommandStatusResponse.ofString("Prepared for exit!");
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }

    @Override
    public void setCallerID(long id) {
        this.callerBackID = id;
    }
}
