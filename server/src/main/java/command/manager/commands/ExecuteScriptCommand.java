package command.manager.commands;

import command.manager.commands.intrface.BaseCommand;
import command.manager.commands.intrface.LocalizableCommand;
import response.CommandStatusResponse;

import java.util.Locale;
import java.util.ResourceBundle;

public class ExecuteScriptCommand implements BaseCommand, LocalizableCommand {

    private CommandStatusResponse response;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("l10n.command.CommandResourceBundle", Locale.US);
    private ResourceBundle commandBundle = ResourceBundle.getBundle("l10n.command.executeS.ExecuteScriptCommandBundle", Locale.US);

    @Override
    public String getName() {
        return resourceBundle.getString("execute_script");
    }

    @Override
    public String getDescr() {
        return resourceBundle.getString("d_execute_script");
    }

    @Override
    public String getArgs() {
        return resourceBundle.getString("a_execute_script");
    }

    @Override
    public void execute(String[] args) throws IllegalArgumentException {
        response = CommandStatusResponse.ofString(commandBundle.getString("Server is alive and ready for command executing!"));
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
        commandBundle = ResourceBundle.getBundle("l10n.command.executeS.ExecuteScriptCommandBundle", locale);
    }
}
