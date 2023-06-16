package command.manager.commands;

import command.manager.CommandManager;
import command.manager.commands.intrface.BaseCommand;
import command.manager.commands.intrface.LocalizableCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import response.CommandStatusResponse;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Shows reference about available commands.
 *
 * @author Zerumi
 * @since 1.0
 */
public class HelpCommand implements BaseCommand, LocalizableCommand {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.help");
    private CommandStatusResponse response;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("l10n.command.CommandResourceBundle", Locale.US);
    private ResourceBundle commandBundle = ResourceBundle.getBundle("l10n.command.help.HelpCommandBundle", Locale.US);

    @Override
    public String getName() {
        return resourceBundle.getString("help");
    }

    @Override
    public String getDescr() {
        return resourceBundle.getString("d_help");
    }

    @Override
    public void execute(String[] args) {
        CommandManager manager = new CommandManager();

        StringBuilder sb = new StringBuilder();

        if (args.length == 1) {
            manager.getCommands().forEach((name, command) -> sb.append(name).append(" ").append(command.getArgs()).append(" --  ").append(command.getDescr()).append('\n'));
        } else {
            for (int i = 1; i < args.length; i++) {
                var command = manager.getCommands().get(args[i]);
                sb.append(args[i]).append(" -- ").append(Optional.ofNullable(command).map(BaseCommand::getDescr).orElse(commandBundle.getString("This command is not found in manager"))).append('\n');
            }
        }

        response = CommandStatusResponse.ofString(sb.toString());
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
        commandBundle = ResourceBundle.getBundle("l10n.command.help.HelpCommandBundle", locale);
    }
}
