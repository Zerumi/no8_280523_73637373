package gui.controller.auth;

import gui.controller.auth.callback.AuthActionListenerCallback;
import gui.frame.RegisterWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterWindowActionListener implements ActionListener {

    private final AuthActionListenerCallback listener;

    public RegisterWindowActionListener(AuthActionListenerCallback listener) {
        this.listener = listener;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        EventQueue.invokeLater(() -> {
            RegisterWindow registerWindow = new RegisterWindow();
            registerWindow.setTitle("Registration");
            registerWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            registerWindow.setVisible(true);
        });

        listener.succeedAction();
    }
}
