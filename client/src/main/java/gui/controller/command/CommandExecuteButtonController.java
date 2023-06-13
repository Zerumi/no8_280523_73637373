package gui.controller.command;

import command.logic.*;
import core.provider.ExceptionProvider;
import exception.CommandsNotLoadedException;
import gui.controller.command.callback.CommandCallback;
import response.logic.ApplicationResponseProvider;
import response.CommandStatusResponse;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class CommandExecuteButtonController implements ActionListener, ApplicationResponseProvider<CommandStatusResponse> {

    private final CommandDescription description;
    private final JTextField argsField;
    private final CommandCallback callback;
    private final ExceptionProvider[] providers;

    public CommandExecuteButtonController(CommandDescription description, JTextField argsField, CommandCallback callback, ExceptionProvider... providers) {
        this.description = description;
        this.argsField = argsField;
        this.callback = callback;
        this.providers = providers;
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
