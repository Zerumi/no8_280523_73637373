package model.validator;

/**
 * Implementation of validator for name field. (Location)
 *
 * @since 1.0
 * @author Zerumi
 */
public class LocationNameValidator implements Validator<String>{
    /**
     * Checks if value not empty.
     *
     * @param value name to validate
     * @return true/false -- matches the restrictions
     * @see model.Location
     */
    @Override
    public boolean validate(String value) {
        if (value == null) return false;
        return value.isEmpty() || value.isBlank();
    }
}
