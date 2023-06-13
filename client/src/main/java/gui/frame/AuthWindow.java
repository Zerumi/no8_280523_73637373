package gui.frame;

import core.provider.ExceptionProvider;
import gui.controller.auth.AuthActionListener;
import gui.controller.auth.AuthTextFieldsEditListener;
import gui.controller.auth.RegisterWindowActionListener;
import gui.controller.auth.callback.AuthActionListenerCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AuthWindow extends JFrame implements AuthActionListenerCallback, ExceptionProvider {
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

        var loginLabel = new JLabel("Login: ", SwingConstants.RIGHT);
        var passLabel = new JLabel("Password: ", SwingConstants.RIGHT);

        authPanel.add(loginLabel);
        authPanel.add(loginField);
        authPanel.add(passLabel);
        authPanel.add(passField);

        var southPanel = new JPanel();

        JButton registerButton = new JButton("Register");
        southPanel.add(registerButton);
        registerButton.addActionListener(new RegisterWindowActionListener(this));

        JButton authButton = new JButton("Login");
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
            JLabel errlbl = new JLabel("Error!", SwingConstants.RIGHT);
            JLabel errmsg = new JLabel(e.toString());
            notifications.add(errlbl);
            notifications.add(errmsg);
            authPanel.add(errlbl);
            authPanel.add(errmsg);
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
