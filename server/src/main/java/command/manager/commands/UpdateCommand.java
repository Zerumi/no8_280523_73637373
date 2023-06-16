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
import util.Utilities;

import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Updates element by its ID.
 *
 * @author Zerumi
 * @since 1.0
 */
public class UpdateCommand implements BaseCommand, ArgumentConsumer<Route>, AuthorizableCommand, LocalizableCommand {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.update");
    private CommandStatusResponse response;
    private Route obj;
    private long callerID;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("l10n.command.CommandResourceBundle", Locale.US);
    private ResourceBundle commandBundle = ResourceBundle.getBundle("l10n.command.update.UpdateCommandBundle", Locale.US);

    @Override
    public String getName() {
        return resourceBundle.getString("update");
    }

    @Override
    public String getDescr() {
        return resourceBundle.getString("d_update");
    }

    @Override
    public String getArgs() {
        return resourceBundle.getString("a_update");
    }

    @Override
    public void execute(String[] args) {
        long id = Optional.ofNullable(Utilities.handleUserInputID(args[1])).orElse(-1L);
        if (id < 0) {
            response = new CommandStatusResponse("You must enter a valid ID", -7);
        }
        response = DBIntegrationUtility.getInstance().updateElementInDBAndCollection(obj, id, callerID).toLocalizedCommandResponse(commandBundle);
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
        commandBundle = ResourceBundle.getBundle("l10n.command.update.UpdateCommandBundle", locale);
    }
}
