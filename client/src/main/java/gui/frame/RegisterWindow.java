package gui.frame;

import core.provider.ExceptionProvider;
import gui.controller.auth.callback.AuthActionListenerCallback;
import gui.controller.register.RegisterActionListener;
import gui.controller.register.RegisterTextFieldsEditListener;
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

public class RegisterWindow extends JFrame implements AuthActionListenerCallback, ExceptionProvider {
    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab8");
    private final ArrayList<Component> notifications = new ArrayList<>();
    private final JPanel authPanel;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("gui.l10n.register.Register");

    public RegisterWindow() {

        authPanel = new JPanel();
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

        var userNameLabel = new JLabel(resourceBundle.getString("username"), SwingConstants.RIGHT);
        var loginLabel = new JLabel(resourceBundle.getString("login"), SwingConstants.RIGHT);
        var passLabel = new JLabel(resourceBundle.getString("password"), SwingConstants.RIGHT);

        authPanel.add(userNameLabel);
        authPanel.add(userNameField);
        authPanel.add(loginLabel);
        authPanel.add(loginField);
        authPanel.add(passLabel);
        authPanel.add(passField);

        var southPanel = new JPanel();

        JButton registerButton = new JButton(resourceBundle.getString("bRegister"));
        southPanel.add(registerButton);
        registerButton.addActionListener(authActionListener);
        registerButton.addActionListener(resetForeground);

        SpringUtilities.makeCompactGrid(authPanel,
                3, 2,
                5, 5,
                0, 0);
        this.add(authPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
        this.pack();
    }

    @Override
    public void acceptException(Exception e) {
        logger.info("repaint?");
        EventQueue.invokeLater(() -> {
            AuthUtilities.showError(e, resourceBundle, notifications, authPanel);
            SpringUtilities.makeCompactGrid(authPanel,
                    3 + notifications.size() / 2, 2,
                    5, 5,
                    0, 0);
            authPanel.revalidate();
            repaint();
            this.pack();
        });
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
                    3, 2,
                    5, 5,
                    0, 0);
            authPanel.revalidate();
            repaint();
            this.pack();
        });
    }
}
