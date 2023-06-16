package gui.controller.command;

import command.logic.*;
import core.provider.ExceptionProvider;
import exception.CommandInterruptedException;
import exception.CommandsNotLoadedException;
import gui.controller.command.callback.CommandCallback;
import gui.l10n.exception.ExceptionLocalizer;
import response.CommandStatusResponse;
import response.logic.ApplicationResponseProvider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CommandExecuteButtonController implements ActionListener, ApplicationResponseProvider<CommandStatusResponse> {
    private final ResourceBundle resourceBundle;
    private final CommandDescription description;
    private final JTextField argsField;
    private final CommandCallback callback;
    private final ExceptionProvider[] providers;

    public CommandExecuteButtonController(CommandDescription description, JTextField argsField, CommandCallback callback, ExceptionProvider... providers) {
        this.description = description;
        this.argsField = argsField;
        this.callback = callback;
        this.providers = providers;
        this.resourceBundle = ResourceBundle.getBundle("gui.l10n.command.CommandWindow");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            CommandExecutor executor = new CommandExecutor(
                    CommandDescriptionHolder.getInstance().getCommands(),
                    null,
                    CommandMode.GUIMode,
                    this);

            executor.executeSingleCommand(description.getName() + " " + argsField.getText());
        } catch (CommandsNotLoadedException ex) {
            CommandLoaderUtility.initializeCommands();
            callback.writeResponse(new CommandStatusResponse(resourceBundle.getString("sww"), -3));
        } catch (CommandInterruptedException ex) {
            callback.writeString(ExceptionLocalizer.localizeException((Exception) ex.getCause()));
        }
    }

    @Override
    public void acceptException(Exception e) {
        Arrays.stream(providers).forEach(x -> x.acceptException(e));
    }

    @Override
    public void acceptResponse(CommandStatusResponse response) {
        callback.writeResponse(response);
    }
}
