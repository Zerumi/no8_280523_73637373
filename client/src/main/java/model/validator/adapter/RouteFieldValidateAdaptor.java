package model.validator.adapter;

import model.RouteFields;
import model.validator.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RouteFieldValidateAdaptor {

    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab8");

    public static boolean validate(RouteFields routeField, Object value) {
        boolean validate;
        try {
            String objStr = String.valueOf(value);

            logger.info("Validating " + objStr + " (Field: " + routeField + ")");

            switch (routeField) {
                case NAME -> validate = new NameValidator().validate(objStr);
                case COORDINATES_X -> validate = new CoordXValidator().validate(Double.valueOf(objStr));
                case COORDINATES_Y -> validate = new CoordYValidator().validate(Float.valueOf(objStr));
                case FROM_X, TO_X -> validate = new LocationXValidator().validate(Float.valueOf(objStr));
                case FROM_Y, TO_Y, FROM_Z, TO_Z -> validate = new LocationYZValidator().validate(Long.valueOf(objStr));
                case FROM_NAME, TO_NAME -> validate = new LocationNameValidator().validate(objStr);
                case DISTANCE -> validate = new DistanceValidator().validate(Integer.valueOf(objStr));
                default -> validate = false;
            }

        } catch (NumberFormatException ex) {
            validate = true;
        }

        return !validate;
    }
}
