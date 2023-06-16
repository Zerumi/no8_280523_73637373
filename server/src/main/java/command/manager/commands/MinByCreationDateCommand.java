package command.manager.commands;

import command.manager.commands.intrface.BaseCommand;
import command.manager.commands.intrface.LocalizableCommand;
import model.Route;
import model.comparator.RouteCreationDateComparator;
import model.handler.CollectionHandler;
import model.handler.RoutesHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import response.CommandStatusResponse;

import java.util.*;

/**
 * Returns element from collection with min creation date.
 *
 * @author Zerumi
 * @since 1.0
 */
public class MinByCreationDateCommand implements BaseCommand, LocalizableCommand {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.minByCD");
    private CommandStatusResponse response;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("l10n.command.CommandResourceBundle", Locale.US);
    private ResourceBundle commandBundle = ResourceBundle.getBundle("l10n.command.minBCD.MinByCreationDateCommandBundle", Locale.US);

    @Override
    public String getName() {
        return resourceBundle.getString("min_by_creation_date");
    }

    @Override
    public String getDescr() {
        return resourceBundle.getString("d_min_by_creation_date");
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();
        Date min = collectionHandler.getCollection().stream().map(Route::getCreationDate).min(Date::compareTo).orElse(null);

        if (min == null)
            response = CommandStatusResponse.ofString(commandBundle.getString("There's nothing to show..."));
        else {
            Optional<Route> optional = collectionHandler.getCollection().stream().min(new RouteCreationDateComparator());
            response = optional.map(route -> CommandStatusResponse.ofString(route.toString())).orElseGet(() -> CommandStatusResponse.ofString(commandBundle.getString("There's nothing to show...")));
        }

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
        commandBundle = ResourceBundle.getBundle("l10n.command.minBCD.MinByCreationDateCommandBundle", locale);
    }
}
