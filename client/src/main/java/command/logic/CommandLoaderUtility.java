package command.logic;

import core.provider.SingleElementProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.logic.sender.CommandDescriptionsRequestSender;

import java.util.ArrayList;

public class CommandLoaderUtility implements SingleElementProvider<ArrayList<CommandDescription>> {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab8");

    public static void initializeCommands() {
        new CommandDescriptionsRequestSender().sendRequestForGetCommands();
    }

    @Override
    public void acceptException(Exception e) {
        logger.error(e);
    }

    @Override
    public void acceptElement(ArrayList<CommandDescription> descriptions) {
        logger.info("maybe it's already accepted.....");
    }
}
