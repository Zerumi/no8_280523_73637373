package gui.controller.main.action;

import gui.frame.VisualizationWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class OpenVisualizationAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        VisualizationWindow window;
        try {
            window = new VisualizationWindow();
            window.setTitle("Routes visualization");
            window.setVisible(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog((Component) e.getSource(), "Something went wrong", "", JOptionPane.ERROR_MESSAGE);
        }
    }
}
