package model.handler.mode.gui;

import exception.BuildObjectException;
import gui.frame.RouteFillWindow;
import model.Route;
import model.handler.ModuleHandler;
import model.validator.RouteValidator;

public class GUIRouteHandler implements ModuleHandler<Route> {

    @Override
    public Route buildObject() throws BuildObjectException {

        RouteFillWindow fillWindow = new RouteFillWindow();
        if (fillWindow.showAsDialog("route builder")) {
            Route result = fillWindow.getRoute();
            if (!new RouteValidator().validate(result)) {
                return result;
            }
        }

        throw new BuildObjectException("invalid obj");

    }
}
