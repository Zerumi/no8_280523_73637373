package command.manager.commands;

import command.manager.commands.intrface.BaseCommand;
import command.manager.commands.intrface.LocalizableCommand;
import model.Route;
import model.handler.CollectionHandler;
import model.handler.RoutesHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import response.CommandStatusResponse;

import java.util.*;

/**
 * Prints all distance fields in ascending sorting.
 *
 * @author Zerumi
 * @since 1.0
 */
public class PrintFieldDistanceAscendingCommand implements BaseCommand, LocalizableCommand {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.printFDA");
    private CommandStatusResponse response;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("l10n.command.CommandResourceBundle", Locale.US);
    private ResourceBundle commandBundle = ResourceBundle.getBundle("l10n.command.printFAD.PrintFieldAscendingDistanceCommandBundle", Locale.US);

    @Override
    public String getName() {
        return resourceBundle.getString("print_field_ascending_distance");
    }

    @Override
    public String getDescr() {
        return resourceBundle.getString("d_print_field_ascending_distance");
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();
        List<Integer> distances = collectionHandler.getCollection().stream().map(Route::getDistance).sorted(Comparator.comparingInt(o -> o)).toList();

        StringBuilder sb = new StringBuilder();
        distances.forEach(d -> sb.append(d).append('\n'));
        response = CommandStatusResponse.ofString(sb.toString());

        if (collectionHandler.getCollection().isEmpty())
            response = CommandStatusResponse.ofString(commandBundle.getString("There's nothing to show..."));

        logger.info(response.getResponse());
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }

    @Override
    public void setLocale(Locale locale) {
        ResourceBundle.clearCache();
        Locale.setDefault(locale);
        resourceBundle = ResourceBundle.getBundle("l10n.command.CommandResourceBundle", locale);
        commandBundle = ResourceBundle.getBundle("l10n.command.printFAD.PrintFieldAscendingDistanceCommandBundle", locale);
    }
}
