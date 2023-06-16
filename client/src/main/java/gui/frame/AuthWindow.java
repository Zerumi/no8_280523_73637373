package gui.frame;

import core.provider.ExceptionProvider;
import gui.controller.auth.AuthActionListener;
import gui.controller.auth.AuthTextFieldsEditListener;
import gui.controller.auth.RegisterWindowActionListener;
import gui.controller.auth.callback.AuthActionListenerCallback;
import gui.l10n.exception.ExceptionLocalizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.AuthUtilities;
import util.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AuthWindow extends JFrame implements AuthActionListenerCallback, ExceptionProvider {
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("gui.l10n.auth.Auth");
    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab8");
    private final ArrayList<Component> notifications = new ArrayList<>();
    private final JPanel authPanel;

    public AuthWindow() {
        authPanel = new JPanel();
        authPanel.setLayout(new SpringLayout());

        var loginField = new JTextField();
        var passField = new JPasswordField();

        AuthTextFieldsEditListener textChangeListener = new AuthTextFieldsEditListener(loginField, passField, this, false);
        AuthActionListener authActionListener = new AuthActionListener(loginField, passField, this, this);
        ActionListener resetForeground = event -> textChangeListener.setEnabled(true);

        loginField.getDocument().addDocumentListener(textChangeListener);
        passField.getDocument().addDocumentListener(textChangeListener);

        loginField.setColumns(12);
        passField.setColumns(12);

        passField.addActionListener(authActionListener);
        passField.addActionListener(resetForeground);

        var loginLabel = new JLabel(resourceBundle.getString("login"), SwingConstants.RIGHT);
        var passLabel = new JLabel(resourceBundle.getString("password"), SwingConstants.RIGHT);

        authPanel.add(loginLabel);
        authPanel.add(loginField);
        authPanel.add(passLabel);
        authPanel.add(passField);

        var southPanel = new JPanel();

        JButton registerButton = new JButton(resourceBundle.getString("bRegister"));
        southPanel.add(registerButton);
        registerButton.addActionListener(new RegisterWindowActionListener(this));

        JButton authButton = new JButton(resourceBundle.getString("bLogin"));
        southPanel.add(authButton);
        authButton.addActionListener(authActionListener);
        authButton.addActionListener(resetForeground);

        SpringUtilities.makeCompactGrid(authPanel,
                2, 2,
                5, 5,
                0, 0);

        this.add(authPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
        this.setLocationByPlatform(true);
        this.pack();
    }

    @Override
    public void succeedAction() {
        this.setVisible(false);
        this.dispose();
    }

    @Override
    public void resetNotifications() {
        EventQueue.invokeLater(() -> {
            notifications.forEach(authPanel::remove);
            notifications.clear();
            SpringUtilities.makeCompactGrid(authPanel,
                    2, 2,
                    5, 5,
                    0, 0);
            authPanel.revalidate();
            repaint();
            this.pack();
        });
    }


    @Override
    public void acceptException(Exception e) {
        logger.info("repaint?");
        EventQueue.invokeLater(() -> {
            AuthUtilities.showError(e, resourceBundle, notifications, authPanel);
            SpringUtilities.makeCompactGrid(authPanel,
                    2 + notifications.size() / 2, 2,
                    5, 5,
                    0, 0);
            authPanel.revalidate();
            repaint();
            this.pack();
        });
    }
}
