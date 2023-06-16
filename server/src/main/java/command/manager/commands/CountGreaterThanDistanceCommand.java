package command.manager.commands;

import command.manager.commands.intrface.BaseCommand;
import command.manager.commands.intrface.LocalizableCommand;
import model.Route;
import model.handler.CollectionHandler;
import model.handler.RoutesHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import response.CommandStatusResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Shows count of the elements greater than distance value.
 *
 * @author Zerumi
 * @since 1.0
 */
public class CountGreaterThanDistanceCommand implements BaseCommand, LocalizableCommand {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.countGTDistance");
    private CommandStatusResponse response;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("l10n.command.CommandResourceBundle", Locale.US);
    private ResourceBundle commandBundle = ResourceBundle.getBundle("l10n.command.countGTD.CountGreaterThanDistanceCommandBundle", Locale.US);

    @Override
    public String getName() {
        return resourceBundle.getString("count_greater_than_distance");
    }

    @Override
    public String getDescr() {
        return resourceBundle.getString("d_count_greater_than_distance");
    }

    @Override
    public String getArgs() {
        return resourceBundle.getString("a_count_greater_than_distance");
    }

    @Override
    public void execute(String[] args) {
        int greaterThan = Integer.parseInt(args[1]);

        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();
        List<Integer> distances = collectionHandler.getCollection().stream().map(Route::getDistance).toList();

        response = CommandStatusResponse.ofString(commandBundle.getString("Total count: ") + distances.stream().map(x -> x.compareTo(greaterThan)).filter(x -> x > 0).count());
        logger.info(response);
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
        commandBundle = ResourceBundle.getBundle("l10n.command.countGTD.CountGreaterThanDistanceCommandBundle", locale);
    }
}
