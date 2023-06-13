package model.validator;

/**
 * Implementation of validator for Coordinates.x field.
 *
 * @since 1.0
 * @author Zerumi
 */
public class CoordXValidator implements Validator<Double> {
    /**
     * Checks if value greater than -107
     *
     * @param value x to validate
     * @return true/false -- matches the restrictions
     * @see model.Coordinates
     */
    @Override
    public boolean validate(Double value) {
        return value <= -107 || !(value <= Double.MAX_VALUE);
    }
}
