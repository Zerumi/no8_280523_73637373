package command.manager.commands;

import command.manager.commands.intrface.ArgumentConsumer;
import command.manager.commands.intrface.AuthorizableCommand;
import command.manager.commands.intrface.BaseCommand;
import command.manager.commands.intrface.LocalizableCommand;
import database.logic.element.DBIntegrationUtility;
import model.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import response.CommandStatusResponse;

import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Adds new element to collection.
 *
 * @author Zerumi
 * @since 1.0
 */
public class AddCommand implements BaseCommand, ArgumentConsumer<Route>, AuthorizableCommand, LocalizableCommand {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.add");
    private CommandStatusResponse response;
    private Route obj;
    private long callerID;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("l10n.command.CommandResourceBundle", Locale.US);
    private ResourceBundle commandBundle = ResourceBundle.getBundle("l10n.command.add.AddCommandBundle", Locale.US);

    @Override
    public void setObj(Route obj) {
        this.obj = obj;
        obj.setCreationDate(Date.from(Instant.now()));
    }

    @Override
    public String getName() {
        return resourceBundle.getString("add");
    }

    @Override
    public String getDescr() {
        return resourceBundle.getString("d_add");
    }

    @Override
    public String getArgs() {
        return resourceBundle.getString("a_add");
    }

    @Override
    public void execute(String[] args) {
        response = DBIntegrationUtility.getInstance().addRouteToDBAndCollection(obj, callerID).toLocalizedCommandResponse(commandBundle);
        logger.info(response.getResponse());
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
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
        commandBundle = ResourceBundle.getBundle("l10n.command.add.AddCommandBundle", locale);
    }
}
