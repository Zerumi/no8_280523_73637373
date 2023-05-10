package commandManager.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

/**
 * Terminates the application (without saving collection).
 *
 * @author Zerumi
 * @since 1.0
 */
public class ExitCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.exit");
    private CommandStatusResponse response;

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
        response = CommandStatusResponse.ofString("Prepared for exit!");
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }
}
