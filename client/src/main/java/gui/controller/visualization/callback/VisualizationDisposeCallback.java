package gui.controller.visualization.callback;

import gui.frame.VisualizationWindow;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VisualizationDisposeCallback extends WindowAdapter {

    private final VisualizationWindow window;

    public VisualizationDisposeCallback(VisualizationWindow window) {
        this.window = window;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        window.dispose();

        super.windowClosing(e);
    }
}
