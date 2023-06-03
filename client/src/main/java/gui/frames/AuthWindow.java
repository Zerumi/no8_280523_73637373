package gui.frames;

import gui.controllers.auth.AuthActionListener;
import gui.controllers.auth.AuthTextFieldsEditListener;
import gui.controllers.auth.RegisterWindowActionListener;
import gui.controllers.auth.callbacks.AuthActionListenerCallback;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AuthWindow extends JFrame implements AuthActionListenerCallback {

    public AuthWindow() {
        var authPanel = new JPanel();
        authPanel.setLayout(new GridLayout(2, 2));

        var loginField = new JTextField();
        var passField = new JPasswordField();

        AuthTextFieldsEditListener textChangeListener = new AuthTextFieldsEditListener(loginField, passField, false);
        AuthActionListener authActionListener = new AuthActionListener(loginField, passField, this);
        ActionListener resetForeground = event -> textChangeListener.setEnabled(true);

        loginField.getDocument().addDocumentListener(textChangeListener);
        passField.getDocument().addDocumentListener(textChangeListener);

        loginField.setColumns(12);
        passField.setColumns(12);

        passField.addActionListener(authActionListener);
        passField.addActionListener(resetForeground);

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
        authButton.addActionListener(authActionListener);
        authButton.addActionListener(resetForeground);

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
