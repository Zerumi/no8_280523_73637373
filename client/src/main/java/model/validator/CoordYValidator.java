package model.validator;

/**
 * Implementation of validator for Coordinates.y field.
 *
 * @since 1.0
 * @author Zerumi
 */
public class CoordYValidator implements Validator<Float> {
    /**
     * Checks if value greater than -39
     *
     * @param value y to validate
     * @return true/false -- matches the restrictions
     * @see model.Coordinates
     */
    @Override
    public boolean validate(Float value) {
        return value == null || value <= -39 || !(value <= Float.MAX_VALUE);
    }
}
