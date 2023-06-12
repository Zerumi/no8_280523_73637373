package gui.frame;

import gui.controller.register.RegisterActionListener;
import gui.controller.register.RegisterTextFieldsEditListener;
import utils.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterWindow extends AuthWindow {

    public RegisterWindow() {
        var authPanel = new JPanel();
        authPanel.setLayout(new SpringLayout());

        var userNameField = new JTextField();
        var loginField = new JTextField();
        var passField = new JPasswordField();

        userNameField.setColumns(12);
        loginField.setColumns(12);
        passField.setColumns(12);

        RegisterTextFieldsEditListener textChangeListener = new RegisterTextFieldsEditListener
                (userNameField, loginField, passField, this, false);
        RegisterActionListener authActionListener = new RegisterActionListener
                (userNameField, loginField, passField, this, this);
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

        SpringUtilities.makeCompactGrid(authPanel,
                2, 2,
                5, 5,
                0, 0);
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
