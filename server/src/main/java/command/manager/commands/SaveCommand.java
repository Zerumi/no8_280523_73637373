package command.manager.commands;

import file.logic.Saver;
import model.Route;
import model.handler.CollectionHandler;
import model.handler.RoutesHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import response.CommandStatusResponse;

import java.util.HashSet;

/**
 * Saves collection to file.
 *
 * @author Zerumi
 * @since 1.0
 * @deprecated Deprecated since 2.0 because file logic now are out of support
 */
@Deprecated
public class SaveCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.save");
    private CommandStatusResponse response;
    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescr() {
        return "Deprecated. Do nothing";
    }

    @Override
    public void execute(String[] args) {
        logger.trace("Saving...");
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();
        Saver<HashSet<Route>, Route> saver = new Saver<>(Route.class);

        saver.saveCollection(collectionHandler.getCollection(), "lab5");

        response = CommandStatusResponse.ofString("[Server] Collection saving executed.");
        logger.info(response.getResponse());
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }
}
