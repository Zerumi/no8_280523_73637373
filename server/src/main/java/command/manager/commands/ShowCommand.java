package command.manager.commands;

import command.manager.commands.intrface.BaseCommand;
import command.manager.commands.intrface.LocalizableCommand;
import model.Route;
import model.handler.CollectionHandler;
import model.handler.RoutesHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import response.CommandStatusResponse;
import util.Utilities;

import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Shows every element of the collection in toString() interpretation.
 *
 * @author Zerumi
 * @since 1.0
 */
public class ShowCommand implements BaseCommand, LocalizableCommand {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.show");
    private CommandStatusResponse response;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("l10n.command.CommandResourceBundle", Locale.US);
    private ResourceBundle commandBundle = ResourceBundle.getBundle("l10n.command.show.ShowCommandBundle", Locale.US);

    @Override
    public String getName() {
        return resourceBundle.getString("show");
    }

    @Override
    public String getArgs() {
        return resourceBundle.getString("a_show");
    }

    @Override
    public String getDescr() {
        return resourceBundle.getString("d_show");
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<HashSet<Route>, Route> handler = RoutesHandler.getInstance();

        StringBuilder sb = new StringBuilder();

        Long pageNumber = 0L;

        if (handler.getCollection().size() > 50 && args.length == 1) {
            sb.append(commandBundle.getString("large_collection")).append("\n");
        }

        if (args.length > 1) {
            pageNumber = Utilities.handleUserInputID(args[1]);

            if (pageNumber == null) {
                response = new CommandStatusResponse(
                        commandBundle.getString("You must enter a valid page number"), -6);
                return;
            }
        }

        handler.getSorted().stream().skip(pageNumber * 50).limit(50)
                .forEach(e -> sb.append(e.toString()).append('\n'));
        response = CommandStatusResponse.ofString(sb.toString());

        if (sb.isEmpty()) {
            response = CommandStatusResponse.ofString(commandBundle.getString("There's nothing to show."));
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
        commandBundle = ResourceBundle.getBundle("l10n.command.show.ShowCommandBundle", locale);
    }
}
