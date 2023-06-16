package gui.frame;


import gui.component.VisualisationComponent;
import gui.controller.main.callback.GetCollectionFromModelCallback;
import gui.controller.visualization.callback.PrintObjInfoCallback;
import gui.controller.visualization.callback.VisualizationDisposeCallback;
import model.Route;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class VisualizationWindow extends JFrame implements PrintObjInfoCallback {

    private final VisualisationComponent component;
    private final JPanel infoPanel;

    public VisualizationWindow(GetCollectionFromModelCallback callback) throws IOException {
        this.component = new VisualisationComponent(callback);

        infoPanel = new JPanel();
        infoPanel.setLayout(new SpringLayout());
        // todo

        this.add(infoPanel, BorderLayout.WEST);
        this.add(component, BorderLayout.CENTER);
        this.addWindowListener(new VisualizationDisposeCallback(this));
        this.setLocationByPlatform(true);
        this.pack();
    }

    @Override
    public void deliverObj(Route route) {
        // todo
    }
}
