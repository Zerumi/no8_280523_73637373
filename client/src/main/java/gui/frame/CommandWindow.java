package gui.frame;

import command.logic.CommandDescription;
import core.provider.ExceptionProvider;
import gui.controller.command.CommandExecuteButtonController;
import gui.controller.command.callback.CommandCallback;
import gui.l10n.exception.ExceptionLocalizer;
import response.CommandStatusResponse;
import util.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class CommandWindow extends JFrame implements CommandCallback, ExceptionProvider {
    private final JTextArea textArea;
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("gui.l10n.command.CommandWindow");

    public CommandWindow(CommandDescription description) {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new SpringLayout());

        JTextField argsField = new JTextField();
        JButton executeButton = new JButton(resourceBundle.getString("bExecute"));
        executeButton.addActionListener(new CommandExecuteButtonController(description, argsField, this, this));

        northPanel.add(new JLabel(resourceBundle.getString("enter_args")));
        northPanel.add(argsField);
        northPanel.add(executeButton);

        SpringUtilities.makeCompactGrid(northPanel, 1, 3, 3, 3, 0, 0);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 1));

        textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane textScrollPane = new JScrollPane(textArea);
        centerPanel.add(textScrollPane);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        this.setLocationByPlatform(true);
        this.setSize(screenWidth / 2, screenHeight / 2);
    }

    @Override
    public void writeResponse(CommandStatusResponse response) {
        EventQueue.invokeLater(() -> {
            textArea.setText(null);
            textArea.append(resourceBundle.getString("status_code") + response.getStatusCode() + "\n\n");
            textArea.append(resourceBundle.getString("response") + "\n" + response.getResponse());
        });
    }

    @Override
    public void writeString(String string) {
        EventQueue.invokeLater(() -> {
            textArea.setText(null);
            textArea.append(string);
        });
    }

    @Override
    public void acceptException(Exception e) {
        EventQueue.invokeLater(() -> {
            textArea.setText(null);
            textArea.append(resourceBundle.getString("error") + "\n\n");
            textArea.append(ExceptionLocalizer.localizeException(e));
        });
    }
}
