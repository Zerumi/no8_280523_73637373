package model.validator;

/**
 * Implementation of validator for name field. (Route)
 *
 * @since 1.0
 * @author Zerumi
 */
public class NameValidator implements Validator<String> {

    /**
     * Checks if value not null and not blank.
     *
     * @param value name to validate
     * @return true/false -- matches the restrictions
     * @see model.Route
     */
    @Override
    public boolean validate(String value) {
        return value == null || value.isEmpty() || value.isBlank();
    }
}
