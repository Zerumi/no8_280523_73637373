package command.manager.commands;

import command.manager.commands.intrface.BaseCommand;
import command.manager.commands.intrface.LocalizableCommand;
import model.Route;
import model.handler.CollectionHandler;
import model.handler.RoutesHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import response.CommandStatusResponse;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Shows information about the collection.
 *
 * @author Zerumi
 * @since 1.0
 */
public class InfoCommand implements BaseCommand, LocalizableCommand {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.info");
    private CommandStatusResponse response;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("l10n.command.CommandResourceBundle", Locale.US);
    private ResourceBundle commandBundle = ResourceBundle.getBundle("l10n.command.info.InfoCommandBundle", Locale.US);

    @Override
    public String getName() {
        return resourceBundle.getString("info");
    }

    @Override
    public String getDescr() {
        return resourceBundle.getString("d_info");
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<HashSet<Route>, Route> handler = RoutesHandler.getInstance();

        HashSet<Route> collection = handler.getCollection();

        MessageFormat mf = new MessageFormat(commandBundle.getString("info"));
        String sb = mf.format(new Object[]{collection.getClass().getName(),
                handler.getFirstOrNew().getClass().getName(),
                collection.size(),
                handler.getInitDate()
        });

        response = CommandStatusResponse.ofString(sb);
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
        commandBundle = ResourceBundle.getBundle("l10n.command.info.InfoCommandBundle", locale);
    }
}
