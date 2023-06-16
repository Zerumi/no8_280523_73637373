package gui.controller.main;

import gui.controller.filter.ModelConnector;
import gui.frame.FilterWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenFilterWindowAction implements ActionListener {

    private final ModelConnector connector;

    public OpenFilterWindowAction(ModelConnector connector) {
        this.connector = connector;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EventQueue.invokeLater(() -> {
            FilterWindow window = new FilterWindow(connector);
            window.setTitle("Filter");
            window.setVisible(true);
        });
    }
}
