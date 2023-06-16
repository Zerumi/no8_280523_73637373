package command.manager.commands;

import client.logic.SessionHandler;
import command.manager.commands.intrface.AuthorizableCommand;
import command.manager.commands.intrface.BaseCommand;
import command.manager.commands.intrface.LocalizableCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import response.CommandStatusResponse;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Terminates the application (without saving collection).
 *
 * @author Zerumi
 * @since 1.0
 */
public class ExitCommand implements BaseCommand, AuthorizableCommand, LocalizableCommand {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.exit");
    private CommandStatusResponse response;
    private long callerBackID;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("l10n.command.CommandResourceBundle", Locale.US);
    private ResourceBundle commandBundle = ResourceBundle.getBundle("l10n.command.exit.ExitCommandBundle", Locale.US);

    @Override
    public String getName() {
        return resourceBundle.getString("exit");
    }

    @Override
    public String getDescr() {
        return resourceBundle.getString("d_exit");
    }

    @Override
    public void execute(String[] args) {
        logger.trace("Invoked exit command.");
        SessionHandler.getInstance().removeSession(callerBackID);
        response = CommandStatusResponse.ofString(commandBundle.getString("Prepared for exit!"));
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }

    @Override
    public void setCallerID(long id) {
        this.callerBackID = id;
    }

    @Override
    public void setLocale(Locale locale) {
        ResourceBundle.clearCache();
        Locale.setDefault(locale);
        resourceBundle = ResourceBundle.getBundle("l10n.command.CommandResourceBundle", locale);
        commandBundle = ResourceBundle.getBundle("l10n.command.exit.ExitCommandBundle", locale);
    }
}
