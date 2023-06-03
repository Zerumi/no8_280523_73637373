package gui.controllers.auth;

import javax.swing.*;
import java.awt.*;

public class RegisterTextFieldsEditListener extends AuthTextFieldsEditListener {

    private final JTextField usernameField;

    public RegisterTextFieldsEditListener(JTextField usernameField, JTextField loginField,
                                          JPasswordField passwordField, boolean isEnabled) {
        super(loginField, passwordField, isEnabled);

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
