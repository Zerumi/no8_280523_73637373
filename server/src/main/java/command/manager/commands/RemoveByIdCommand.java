package command.manager.commands;

import command.manager.commands.intrface.AuthorizableCommand;
import command.manager.commands.intrface.BaseCommand;
import command.manager.commands.intrface.LocalizableCommand;
import database.logic.element.DBIntegrationUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import response.CommandStatusResponse;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Removes element from collection by id.
 *
 * @author Zerumi
 * @since 1.0
 */
public class RemoveByIdCommand implements BaseCommand, AuthorizableCommand, LocalizableCommand {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.rmByID");
    private CommandStatusResponse response;
    private long callerID;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("l10n.command.CommandResourceBundle", Locale.US);
    private ResourceBundle commandBundle = ResourceBundle.getBundle("l10n.command.remove.RemoveByIdCommandBundle", Locale.US);

    @Override
    public String getName() {
        return resourceBundle.getString("remove_by_id");
    }

    @Override
    public String getDescr() {
        return resourceBundle.getString("d_remove_by_id");
    }

    @Override
    public String getArgs() {
        return resourceBundle.getString("a_remove_by_id");
    }

    @Override
    public void execute(String[] args) {
        if (!DBIntegrationUtility.getInstance().removeFromCollectionAndDB(callerID, Long.parseLong(args[1])))
            response = new CommandStatusResponse(commandBundle.getString("Element with that id doesn't exists or you don't have access to edit this object."), -922);
        else
            response = CommandStatusResponse.ofString(commandBundle.getString("Executed"));

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
        commandBundle = ResourceBundle.getBundle("l10n.command.remove.RemoveByIdCommandBundle", locale);
    }
}
