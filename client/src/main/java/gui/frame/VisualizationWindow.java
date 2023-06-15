package gui.frame;


import gui.component.VisualisationComponent;
import gui.controller.main.callback.GetCollectionFromModelCallback;
import gui.controller.visualization.callback.VisualizationDisposeCallback;

import javax.swing.*;
import java.io.IOException;

public class VisualizationWindow extends JFrame {

    private final VisualisationComponent component;

    public VisualizationWindow(GetCollectionFromModelCallback callback) throws IOException {
        this.component = new VisualisationComponent(callback);

        this.add(component);
        this.addWindowListener(new VisualizationDisposeCallback(this));
        this.setLocationByPlatform(true);
        this.pack();
    }
}
