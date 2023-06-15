package model.handler.mode.gui;

import exception.BuildObjectException;
import gui.frame.RouteFillWindow;
import model.Route;
import model.handler.ModuleHandler;

public class GUIRouteHandler implements ModuleHandler<Route> {

    @Override
    public Route buildObject() throws BuildObjectException {

        RouteFillWindow fillWindow = new RouteFillWindow();
        if (fillWindow.showAsDialog("route builder")) {
            return fillWindow.getRoute();
        } else throw new BuildObjectException("interrupted by user");

    }
}
