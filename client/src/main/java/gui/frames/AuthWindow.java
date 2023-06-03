package gui.frames;

import gui.controllers.AuthActionListener;
import gui.controllers.RegisterWindowActionListener;
import gui.controllers.callbacks.AuthActionListenerCallback;

import javax.swing.*;
import java.awt.*;

public class AuthWindow extends JFrame implements AuthActionListenerCallback {

    public AuthWindow() {
        var authPanel = new JPanel();
        authPanel.setLayout(new GridLayout(2, 2));

        var loginField = new JTextField();
        var passField = new JPasswordField();

        loginField.setColumns(15);
        passField.setColumns(15);

        passField.addActionListener(new AuthActionListener(loginField, passField, this));

        var loginLabel = new JLabel("Login: ", SwingConstants.RIGHT);
        var passLabel = new JLabel("Password: ", SwingConstants.RIGHT);

        authPanel.add(loginLabel);
        authPanel.add(loginField);
        authPanel.add(passLabel);
        authPanel.add(passField);

        var southPanel = new JPanel();

        JButton registerButton = new JButton("Register");
        southPanel.add(registerButton);
        registerButton.addActionListener(new RegisterWindowActionListener(this));

        JButton authButton = new JButton("Login");
        southPanel.add(authButton);
        authButton.addActionListener(new AuthActionListener(loginField, passField, this));

        this.add(authPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
        this.pack();
    }

    @Override
    public void succeedAction() {
        this.setVisible(false);
        this.dispose();
    }
}