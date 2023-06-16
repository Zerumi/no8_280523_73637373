package gui.controller.main;

import command.logic.CommandDescription;
import core.provider.ExceptionProvider;
import core.provider.SingleElementProvider;
import gui.controller.main.callback.RepaintCallback;
import request.logic.sender.CommandDescriptionsRequestSender;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CommandButtonFactory implements SingleElementProvider<ArrayList<CommandDescription>> {

    private final JPanel panelToFill;
    private final RepaintCallback callback;
    private final ExceptionProvider[] providers;
    private ResourceBundle bundle = ResourceBundle.getBundle("gui.l10n.command.Commands");
    private ArrayList<CommandDescription> descriptions;

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
        this.descriptions = descriptions;
        EventQueue.invokeLater(() -> {
            for (var description : descriptions) {
                JButton commandButton = new JButton(bundle.getString(description.getName()));
                commandButton.addActionListener(new CommandButtonAction(description));
                panelToFill.add(commandButton);
            }
            panelToFill.invalidate();
            callback.callRepaint();
        });
    }

    public void fillAsync() {
        new CommandDescriptionsRequestSender().sendRequestForGetCommands(this);
    }

    public void changeLocale() {
        ResourceBundle.clearCache();
        bundle = ResourceBundle.getBundle("gui.l10n.command.Commands");
        int i = 0;
        for (Component component : panelToFill.getComponents()) {
            if (component instanceof JButton bComponent) {
                bComponent.setText(bundle.getString(descriptions.get(i++).getName()));
            }
        }
    }
}
