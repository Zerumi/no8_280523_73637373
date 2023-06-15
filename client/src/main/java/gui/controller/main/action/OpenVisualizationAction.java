package gui.controller.main.action;

import gui.controller.main.callback.GetCollectionFromModelCallback;
import gui.frame.VisualizationWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class OpenVisualizationAction extends AbstractAction {

    private final GetCollectionFromModelCallback callback;

    public OpenVisualizationAction(GetCollectionFromModelCallback callback) {
        this.callback = callback;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VisualizationWindow window;
        try {
            window = new VisualizationWindow(callback);
            window.setTitle("Routes visualization");
            window.setVisible(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog((Component) e.getSource(), "Something went wrong", "", JOptionPane.ERROR_MESSAGE);
        }
    }
}
