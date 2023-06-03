package gui.controllers.auth;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class AuthTextFieldsEditListener implements DocumentListener {

    private final JTextField loginField;
    private final JPasswordField passwordField;
    private boolean isEnabled;

    public AuthTextFieldsEditListener(JTextField loginField, JPasswordField passwordField, boolean isEnabled) {
        this.loginField = loginField;
        this.passwordField = passwordField;
        this.isEnabled = isEnabled;
    }

    /**
     * Gives notification that there was an insert into the document.  The
     * range given by the DocumentEvent bounds the freshly inserted region.
     *
     * @param e the document event
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        tryToUpdateTextFields();
    }

    /**
     * Gives notification that a portion of the document has been
     * removed.  The range is given in terms of what the view last
     * saw (that is, before updating sticky positions).
     *
     * @param e the document event
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        tryToUpdateTextFields();
    }

    /**
     * Gives notification that an attribute or set of attributes changed.
     *
     * @param e the document event
     */
    @Override
    public void changedUpdate(DocumentEvent e) {
        tryToUpdateTextFields();
    }

    public void tryToUpdateTextFields() {
        if (isEnabled) {
            loginField.setForeground(Color.BLACK);
            passwordField.setForeground(Color.BLACK);
            isEnabled = false;
        }
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
