package gui.controllers;

import authentication.AuthLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responseLogic.ApplicationResponseProvider;
import responses.AuthorizeResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthButtonActionListener implements ActionListener, ApplicationResponseProvider<AuthorizeResponse> {
    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab8");
    private final JTextField usernameField;

    private final JPasswordField passwordField;

    public AuthButtonActionListener(JTextField usernameField, JPasswordField passwordField) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        new AuthLogic().auth(usernameField.getText(), passwordField.getPassword());
    }

    @Override
    public void acceptException(Exception e) {
        logger.error(e);
    }

    @Override
    public void acceptResponse(AuthorizeResponse response) {
        usernameField.setForeground(Color.GREEN);
    }
}
