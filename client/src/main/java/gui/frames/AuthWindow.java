package gui.frames;

import gui.controllers.AuthButtonActionListener;

import javax.swing.*;
import java.awt.*;

public class AuthWindow extends JFrame {

    public AuthWindow() {
        var authPanel = new JPanel();
        authPanel.setLayout(new GridLayout(2, 2));

        var loginField = new JTextField();
        var passField = new JPasswordField();

        var loginLabel = new JLabel("Login: ", SwingConstants.RIGHT);
        var passLabel = new JLabel("Password: ", SwingConstants.RIGHT);

        authPanel.add(loginLabel);
        authPanel.add(loginField);
        authPanel.add(passLabel);
        authPanel.add(passField);

        var southPanel = new JPanel();

        JButton authButton = new JButton("Auth");
        southPanel.add(authButton);
        authButton.addActionListener(new AuthButtonActionListener());
    }
}
