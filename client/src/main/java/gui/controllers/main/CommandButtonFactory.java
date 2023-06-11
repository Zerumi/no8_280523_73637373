package gui.controllers.main;

import command.logic.CommandDescription;
import core.providers.ExceptionProvider;
import core.providers.SingleElementProvider;
import gui.controllers.main.callbacks.RepaintCallback;
import request.logic.senders.CommandDescriptionsRequestSender;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandButtonFactory implements SingleElementProvider<ArrayList<CommandDescription>> {

    private final JPanel panelToFill;
    private final RepaintCallback callback;
    private final ExceptionProvider[] providers;

    public CommandButtonFactory(JPanel panelToFill, RepaintCallback callback, ExceptionProvider... providers) {
        this.panelToFill = panelToFill;
        this.callback = callback;
        this.providers = providers;
    }

    @Override
    public void acceptException(Exception e) {
        Arrays.stream(providers).forEach(x -> x.acceptException(e));
    }

    @Override
    public void acceptElement(ArrayList<CommandDescription> descriptions) {
        for (var description : descriptions) {
            JButton commandButton = new JButton(description.getName());
            commandButton.addActionListener(new CommandButtonAction());
            panelToFill.add(commandButton);
        }
        panelToFill.invalidate();
        callback.callRepaint();
    }

    public void fillAsync() {
        new CommandDescriptionsRequestSender().sendRequestForGetCommands(this);
    }
}
