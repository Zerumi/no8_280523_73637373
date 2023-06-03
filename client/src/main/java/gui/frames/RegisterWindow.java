package gui.frames;

import gui.controllers.auth.RegisterActionListener;
import gui.controllers.auth.RegisterTextFieldsEditListener;
import gui.controllers.auth.callbacks.AuthActionListenerCallback;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterWindow extends JFrame implements AuthActionListenerCallback {

    public RegisterWindow() {
        var authPanel = new JPanel();
        authPanel.setLayout(new GridLayout(3, 2));

        var userNameField = new JTextField();
        var loginField = new JTextField();
        var passField = new JPasswordField();

        userNameField.setColumns(12);
        loginField.setColumns(12);
        passField.setColumns(12);

        RegisterTextFieldsEditListener textChangeListener = new RegisterTextFieldsEditListener
                (userNameField, loginField, passField, false);
        RegisterActionListener authActionListener = new RegisterActionListener
                (userNameField, loginField, passField, this);
        ActionListener resetForeground = event -> textChangeListener.setEnabled(true);

        userNameField.getDocument().addDocumentListener(textChangeListener);
        loginField.getDocument().addDocumentListener(textChangeListener);
        passField.getDocument().addDocumentListener(textChangeListener);

        passField.addActionListener(authActionListener);
        passField.addActionListener(resetForeground);

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
        registerButton.addActionListener(authActionListener);
        registerButton.addActionListener(resetForeground);

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
