package util;

import gui.l10n.exception.ExceptionLocalizer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AuthUtilities {
    public static void showError(Exception e, ResourceBundle resourceBundle, ArrayList<Component> notifications, JPanel authPanel) {
        JLabel errlbl = new JLabel(resourceBundle.getString("error"), SwingConstants.RIGHT);
        JLabel errmsg = new JLabel(ExceptionLocalizer.localizeException(e));
        notifications.add(errlbl);
        notifications.add(errmsg);
        authPanel.add(errlbl);
        authPanel.add(errmsg);
    }
}
