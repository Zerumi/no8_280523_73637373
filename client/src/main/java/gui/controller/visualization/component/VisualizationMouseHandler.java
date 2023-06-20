package gui.controller.visualization.component;

import gui.component.util.RoutePoint;
import gui.controller.visualization.callback.PrintObjInfoCallback;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VisualizationMouseHandler extends MouseAdapter {

    private final VisualizationComponentProvider provider;
    private final PrintObjInfoCallback callback;

    public VisualizationMouseHandler(VisualizationComponentProvider provider, PrintObjInfoCallback callback) {
        this.provider = provider;
        this.callback = callback;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        RoutePoint point = provider.find(e.getPoint());
        if (point != null) callback.deliverObj(point.getRoute());
        super.mousePressed(e);
    }
}
