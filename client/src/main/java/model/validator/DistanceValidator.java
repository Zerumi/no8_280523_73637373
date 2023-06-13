package model.validator;

/**
 * Implementation of validator for distance field. (Route)
 *
 * @since 1.0
 * @author Zerumi
 */
public class DistanceValidator implements Validator<Integer> {
    /**
     * Checks if value greater than 1
     *
     * @param value distance to validate
     * @return true/false -- matches the restrictions
     * @see model.Route
     */
    @Override
    public boolean validate(Integer value) {
        return value <= 1;
    }
}
