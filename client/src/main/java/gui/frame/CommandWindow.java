package gui.frame;

import command.logic.CommandDescription;
import core.provider.ExceptionProvider;
import gui.controller.command.callback.CommandCallback;
import gui.controller.command.CommandExecuteButtonController;
import response.CommandStatusResponse;
import util.SpringUtilities;

import javax.swing.*;
import java.awt.*;

public class CommandWindow extends JFrame implements CommandCallback, ExceptionProvider {
    private final JTextArea textArea;

    public CommandWindow(CommandDescription description) {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new SpringLayout());

        JTextField argsField = new JTextField();
        JButton executeButton = new JButton("Execute");
        executeButton.addActionListener(new CommandExecuteButtonController(description, argsField, this, this));

        northPanel.add(new JLabel("Enter arguments: "));
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
            textArea.append("Status Code: " + response.getStatusCode() + "\n\n");
            textArea.append("Response: \n" + response.getResponse());

        });
    }

    @Override
    public void acceptException(Exception e) {
        EventQueue.invokeLater(() -> {
            textArea.setText(null);
            textArea.append("Status Code: Error!\n\n");
            textArea.append(e.toString());
        });
    }
}
