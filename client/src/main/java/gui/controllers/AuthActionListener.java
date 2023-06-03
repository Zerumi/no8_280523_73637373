package gui.controllers;

import authentication.AuthLogic;
import gui.controllers.callbacks.AuthActionListenerCallback;
import gui.frames.MainWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responseLogic.ApplicationResponseProvider;
import responses.AuthorizeResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthActionListener implements ActionListener, ApplicationResponseProvider<AuthorizeResponse> {
    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab8");
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final AuthActionListenerCallback callback;

    public AuthActionListener(JTextField usernameField, JPasswordField passwordField, AuthActionListenerCallback callback) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.callback = callback;
    }

    protected JTextField getLoginField() {
        return usernameField;
    }

    protected JPasswordField getPasswordField() {
        return passwordField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new AuthLogic().auth(usernameField.getText(), passwordField.getPassword(), this);
    }

    @Override
    public void acceptException(Exception e) {
        usernameField.setForeground(Color.RED);
        passwordField.setForeground(Color.RED);
        logger.error(e);
    }

    @Override
    public void acceptResponse(AuthorizeResponse response) {
        usernameField.setForeground(Color.GREEN);
        passwordField.setForeground(Color.GREEN);

        EventQueue.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setTitle("Route collection manager by Zerumi");
            mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mainWindow.setVisible(true);
        });

        callback.succeedAction();
    }
}
