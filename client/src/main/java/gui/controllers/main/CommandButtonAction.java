package gui.controllers.main;

import command.logic.CommandDescription;
import gui.frames.CommandWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommandButtonAction implements ActionListener {

    private final CommandDescription description;

    public CommandButtonAction(CommandDescription description) {
        this.description = description;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CommandWindow commandWindow = new CommandWindow(description);
        commandWindow.setTitle("Command execution: " + description.getName());
        commandWindow.setVisible(true);
    }
}
