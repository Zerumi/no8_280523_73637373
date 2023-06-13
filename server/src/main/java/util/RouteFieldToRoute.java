package util;

import exception.NotEditableException;
import model.Route;
import model.RouteFields;

public class RouteFieldToRoute {
    public static void setField(Route route, RouteFields routeField, Object newValue) throws NotEditableException {
        String strInterpretation = String.valueOf(newValue);
        switch (routeField) {
            case NAME -> route.setName(strInterpretation);
            case COORDINATES_X -> route.getCoordinates().setX(Double.parseDouble(strInterpretation));
            case COORDINATES_Y -> route.getCoordinates().setY(Float.valueOf(strInterpretation));
            case FROM_X -> route.getFrom().setX(Float.parseFloat(strInterpretation));
            case FROM_Y -> route.getFrom().setY(Long.valueOf(strInterpretation));
            case FROM_Z -> route.getFrom().setZ(Long.valueOf(strInterpretation));
            case FROM_NAME -> route.getFrom().setName(strInterpretation);
            case TO_X -> route.getTo().setX(Float.parseFloat(strInterpretation));
            case TO_Y -> route.getTo().setY(Long.valueOf(strInterpretation));
            case TO_Z -> route.getTo().setZ(Long.valueOf(strInterpretation));
            case TO_NAME -> route.getTo().setName(strInterpretation);
            case DISTANCE -> route.setDistance(Integer.parseInt(strInterpretation));
            default -> throw new NotEditableException("Field is not editable.");
        }
    }
}
