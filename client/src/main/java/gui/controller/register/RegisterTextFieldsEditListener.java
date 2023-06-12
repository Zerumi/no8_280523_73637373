package gui.controller.register;

import gui.controller.auth.AuthTextFieldsEditListener;
import gui.controller.auth.callback.AuthActionListenerCallback;

import javax.swing.*;
import java.awt.*;

public class RegisterTextFieldsEditListener extends AuthTextFieldsEditListener {

    private final JTextField usernameField;

    public RegisterTextFieldsEditListener(JTextField usernameField, JTextField loginField,
                                          JPasswordField passwordField, AuthActionListenerCallback callback, boolean isEnabled) {
        super(loginField, passwordField, callback, isEnabled);

        this.usernameField = usernameField;
    }

    @Override
    public void tryToUpdateTextFields() {
        if (isEnabled()) {
            usernameField.setForeground(Color.BLACK);
            super.tryToUpdateTextFields();
        }
    }
}
