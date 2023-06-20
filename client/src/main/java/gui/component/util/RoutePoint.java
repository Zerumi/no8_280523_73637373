package gui.component.util;

import model.Route;

import java.awt.geom.Ellipse2D;

public class RoutePoint {
    private final Route route;
    private final Ellipse2D routeTo;
    private final Ellipse2D routeFrom;

    public RoutePoint(Route route, Ellipse2D routeTo, Ellipse2D routeFrom) {
        this.route = route;
        this.routeTo = routeTo;
        this.routeFrom = routeFrom;
    }

    public Route getRoute() {
        return route;
    }

    public Ellipse2D getRouteFrom() {
        return routeFrom;
    }

    public Ellipse2D getRouteTo() {
        return routeTo;
    }
}
