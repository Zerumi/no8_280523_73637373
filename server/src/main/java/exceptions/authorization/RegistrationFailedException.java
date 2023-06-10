package exceptions.authorization;

public class RegistrationFailedException extends AuthorizeException {
    public RegistrationFailedException(String msg) {
        super(msg);
    }
}
