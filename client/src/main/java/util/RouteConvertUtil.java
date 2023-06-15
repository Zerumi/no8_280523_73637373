package util;

import model.RouteFields;

import java.text.DateFormat;
import java.text.ParseException;

public class RouteConvertUtil {
    public static Object convert(RouteFields field, Object obj) {
        String objVal = String.valueOf(obj);
        switch (field) {
            case ID, FROM_Y, FROM_Z, TO_Y, TO_Z -> {
                return Long.valueOf(objVal);
            }
            case NAME, FROM_NAME, TO_NAME -> {
                return objVal;
            }
            case COORDINATES_X -> {
                return Double.valueOf(objVal);
            }
            case COORDINATES_Y, FROM_X, TO_X -> {
                return Float.valueOf(objVal);
            }
            case CREATION_DATE -> {
                try {
                    return DateFormat.getDateInstance().parse(objVal);
                } catch (ParseException e) {
                    return obj;
                }
            }
            case DISTANCE -> {
                return Integer.valueOf(objVal);
            }
            default -> {
                return obj;
            }
        }
    }
}
