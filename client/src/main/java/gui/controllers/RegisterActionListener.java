package gui.controllers;

import authorization.authCredentials.RegistrationData;
import gui.controllers.callbacks.AuthActionListenerCallback;
import requestLogic.requestSenders.RegistrationRequestSender;
import responseLogic.ApplicationResponseProvider;
import responses.AuthorizeResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterActionListener extends AuthActionListener implements ActionListener, ApplicationResponseProvider<AuthorizeResponse> {
    private final JTextField userNameField;

    public RegisterActionListener(JTextField userNameField, JTextField loginField, JPasswordField passwordField, AuthActionListenerCallback callback) {
        super(loginField, passwordField, callback);
        this.userNameField = userNameField;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        RegistrationData data = new RegistrationData(userNameField.getText(), getLoginField().getText(), getPasswordField().getPassword());
        new RegistrationRequestSender().sendRegisterRequest(data, this);
    }

    @Override
    public void acceptException(Exception e) {
        userNameField.setForeground(Color.RED);
        super.acceptException(e);
    }

    @Override
    public void acceptResponse(AuthorizeResponse response) {
        userNameField.setForeground(Color.GREEN);
        super.acceptResponse(response);
    }
}
