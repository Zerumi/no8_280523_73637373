package util;

import model.Route;
import model.RouteFields;

public class RouteFieldSetters {
    public static void setValue(Route route, RouteFields routeField, Object value) {
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
}
