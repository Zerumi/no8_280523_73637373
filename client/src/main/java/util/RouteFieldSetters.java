package util;

import model.Coordinates;
import model.Location;
import model.Route;
import model.RouteFields;

public class RouteFieldSetters {
    public static void setValue(Route route, RouteFields routeField, Object value) {
        if (route == null) return;
        handleNonNullValue(route, routeField);
        String objStr = String.valueOf(value);
        switch (routeField) {
            case NAME -> route.setName(objStr);
            case COORDINATES_X -> route.getCoordinates().setX(Double.parseDouble(objStr));
            case COORDINATES_Y -> route.getCoordinates().setY(Float.valueOf(objStr));
            case FROM_X -> route.getFrom().setX(Float.parseFloat(objStr));
            case FROM_Y -> route.getFrom().setY(Long.valueOf(objStr));
            case FROM_Z -> route.getFrom().setZ(Long.valueOf(objStr));
            case FROM_NAME -> route.getFrom().setName(objStr);
            case TO_X -> route.getTo().setX(Float.parseFloat(objStr));
            case TO_Y -> route.getTo().setY(Long.valueOf(objStr));
            case TO_Z -> route.getTo().setZ(Long.valueOf(objStr));
            case TO_NAME -> route.getTo().setName(objStr);
            case DISTANCE -> route.setDistance(Integer.parseInt(objStr));
        }
    }

    private static void handleNonNullValue(Route route, RouteFields routeField) {
        switch (routeField) {
            case COORDINATES_X, COORDINATES_Y -> {
                if (route.getCoordinates() == null)
                    route.setCoordinates(new Coordinates());
            }
            case FROM_X, FROM_Y, FROM_Z, FROM_NAME -> {
                if (route.getFrom() == null)
                    route.setFrom(new Location());
            }
            case TO_X, TO_Y, TO_Z, TO_NAME -> {
                if (route.getTo() == null)
                    route.setTo(new Location());
            }
        }
    }
}
