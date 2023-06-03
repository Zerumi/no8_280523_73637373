package gui.frames;

import gui.controllers.AuthActionListener;
import gui.controllers.RegisterActionListener;
import gui.controllers.callbacks.AuthActionListenerCallback;

import javax.swing.*;
import java.awt.*;

public class RegisterWindow extends JFrame implements AuthActionListenerCallback {

    public RegisterWindow() {
        var authPanel = new JPanel();
        authPanel.setLayout(new GridLayout(3, 2));

        var userNameField = new JTextField();
        var loginField = new JTextField();
        var passField = new JPasswordField();

        userNameField.setColumns(15);
        loginField.setColumns(15);
        passField.setColumns(15);

        passField.addActionListener(new AuthActionListener(loginField, passField, this));

        var userNameLabel = new JLabel("Username: ", SwingConstants.RIGHT);
        var loginLabel = new JLabel("Login: ", SwingConstants.RIGHT);
        var passLabel = new JLabel("Password: ", SwingConstants.RIGHT);

        authPanel.add(userNameLabel);
        authPanel.add(userNameField);
        authPanel.add(loginLabel);
        authPanel.add(loginField);
        authPanel.add(passLabel);
        authPanel.add(passField);

        var southPanel = new JPanel();

        JButton registerButton = new JButton("Register");
        southPanel.add(registerButton);
        registerButton.addActionListener(new RegisterActionListener(userNameField, loginField, passField, this));

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
