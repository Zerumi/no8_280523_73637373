package gui.controller.visualization.component;

import gui.component.util.RoutePoint;

import java.awt.geom.Point2D;

public interface VisualizationComponentProvider {
    RoutePoint find(Point2D point2D);
}
