package gui.controller.auth;

import authentication.AuthLogic;
import core.provider.ExceptionProvider;
import exception.DenyOperationException;
import gui.controller.auth.callback.AuthActionListenerCallback;
import gui.frame.MainWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import response.logic.ApplicationResponseProvider;
import response.AuthorizeResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// todo: block operation during executing? ili i tak soidet...
public class AuthActionListener implements ActionListener, ApplicationResponseProvider<AuthorizeResponse> {
    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab8");
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final AuthActionListenerCallback callback;
    private final ExceptionProvider provider;
    private final AuthLogic authLogic = new AuthLogic();

    public AuthActionListener(JTextField usernameField, JPasswordField passwordField,
                              AuthActionListenerCallback callback, ExceptionProvider provider) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.callback = callback;
        this.provider = provider;
    }

    protected JTextField getLoginField() {
        return usernameField;
    }

    protected JPasswordField getPasswordField() {
        return passwordField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        callback.resetNotifications();
        authLogic.auth(usernameField.getText(), passwordField.getPassword(), this);
    }

    @Override
    public void acceptException(Exception e) {
        usernameField.setForeground(Color.RED);
        passwordField.setForeground(Color.RED);
        logger.error(e);
        provider.acceptException(e);
    }

    @Override
    public void acceptResponse(AuthorizeResponse response) {
        usernameField.setForeground(Color.GREEN);
        passwordField.setForeground(Color.GREEN);

        EventQueue.invokeLater(() -> {
            MainWindow mainWindow;
            try {
                mainWindow = new MainWindow(response.getAuthorizedAs());
                mainWindow.setTitle("Route collection manager by Zerumi");
                mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                mainWindow.setVisible(true);
            } catch (DenyOperationException e) {
                logger.warn("Denied new MainWindow");
            }
        });

        callback.succeedAction();
    }
}
