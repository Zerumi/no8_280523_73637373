package util;

import model.RouteFields;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;

public class RouteConvertUtil {

    public static final RouteFields[] NUMERIC_FIELDS =
            {
                    RouteFields.ID,
                    RouteFields.COORDINATES_X,
                    RouteFields.COORDINATES_Y,
                    RouteFields.TO_X,
                    RouteFields.TO_Y,
                    RouteFields.TO_Z,
                    RouteFields.FROM_X,
                    RouteFields.FROM_Y,
                    RouteFields.FROM_Z,
                    RouteFields.DISTANCE
            };

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
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab8");

    public static boolean isNumber(RouteFields routeField) {
        logger.info("is " + routeField + " number?");
        return Arrays.asList(NUMERIC_FIELDS).contains(routeField);
    }
}
