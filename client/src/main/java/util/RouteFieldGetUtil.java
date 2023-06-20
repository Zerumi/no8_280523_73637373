package util;

import model.Route;
import model.RouteFields;

public class RouteFieldGetUtil {
    public static String getStr(Route route, RouteFields field) {
        switch (field) {
            case ID -> {
                return route.getId().toString();
            }
            case NAME -> {
                return route.getName();
            }
            case COORDINATES_X -> {
                return String.valueOf(route.getCoordinates().getX());
            }
            case COORDINATES_Y -> {
                return route.getCoordinates().getY().toString();
            }
            case CREATION_DATE -> {
                return route.getCreationDate().toString();
            }
            case FROM_X -> {
                return String.valueOf(route.getFrom().getX());
            }
            case FROM_Y -> {
                return route.getFrom().getY().toString();
            }
            case FROM_Z -> {
                return route.getFrom().getZ().toString();
            }
            case FROM_NAME -> {
                return route.getFrom().getName();
            }
            case TO_X -> {
                return String.valueOf(route.getTo().getX());
            }
            case TO_Y -> {
                return route.getTo().getY().toString();
            }
            case TO_Z -> {
                return route.getTo().getZ().toString();
            }
            case TO_NAME -> {
                return route.getTo().getName();
            }
            case DISTANCE -> {
                return String.valueOf(route.getDistance());
            }
            default -> {
                return "";
            }
        }
    }
}
