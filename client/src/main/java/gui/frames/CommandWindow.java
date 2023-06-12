package gui.frames;

import command.logic.CommandDescription;
import core.providers.ExceptionProvider;
import gui.controllers.command.CommandCallback;
import gui.controllers.command.CommandExecuteButtonController;
import responses.CommandStatusResponse;
import utils.SpringUtilities;

import javax.swing.*;
import java.awt.*;

public class CommandWindow extends JFrame implements CommandCallback, ExceptionProvider {
    private final TextArea textArea;

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

        textArea = new TextArea();
        textArea.setEditable(false);
        centerPanel.add(textArea);

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
        textArea.setText(null);
        textArea.append("Status Code: " + response.getStatusCode() + "\n\n");
        textArea.append("Response: " + response.getResponse());
    }

    @Override
    public void acceptException(Exception e) {
        textArea.setText(null);
        textArea.append("Status Code: Error!\n\n");
        textArea.append(e.toString());
    }
}
