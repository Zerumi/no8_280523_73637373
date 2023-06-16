package command.manager.commands;

import command.manager.commands.intrface.ArgumentConsumer;
import command.manager.commands.intrface.AuthorizableCommand;
import command.manager.commands.intrface.BaseCommand;
import command.manager.commands.intrface.LocalizableCommand;
import database.logic.element.DBIntegrationUtility;
import model.Route;
import model.comparator.RouteDistanceComparator;
import model.handler.CollectionHandler;
import model.handler.RoutesHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import response.CommandStatusResponse;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Adds element if it's value lower than min value.
 *
 * @author Zerumi
 * @since 1.0
 */
public class AddIfMinCommand implements BaseCommand, ArgumentConsumer<Route>, AuthorizableCommand, LocalizableCommand {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.addIfMin");
    private CommandStatusResponse response;
    private Route obj;
    private long callerID;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("l10n.command.CommandResourceBundle", Locale.US);
    private ResourceBundle commandBundle = ResourceBundle.getBundle("l10n.command.addIfMin.AddIfMinCommandBundle", Locale.US);

    @Override
    public String getName() {
        return resourceBundle.getString("add_if_min");
    }

    @Override
    public String getDescr() {
        return resourceBundle.getString("d_add_if_min");
    }

    @Override
    public String getArgs() {
        return "a_add_if_min";
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();

        if (obj.compareTo(collectionHandler.getMin(new RouteDistanceComparator())) < 0) {
            response = DBIntegrationUtility.getInstance().addRouteToDBAndCollection(obj, callerID).toCommandResponse();
        } else {
            response = new CommandStatusResponse("Element not added: it's not lower than min value.", 3);
        }

        logger.info(response.getResponse());
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }

    @Override
    public void setObj(Route obj) {
        this.obj = obj;
        obj.setCreationDate(Date.from(Instant.now()));
    }

    @Override
    public void setCallerID(long id) {
        this.callerID = id;
    }

    @Override
    public void setLocale(Locale locale) {
        ResourceBundle.clearCache();
        Locale.setDefault(locale);
        resourceBundle = ResourceBundle.getBundle("l10n.command.CommandResourceBundle", locale);
        commandBundle = ResourceBundle.getBundle("l10n.command.addIfMin.AddIfMinCommandBundle", locale);
    }
}
