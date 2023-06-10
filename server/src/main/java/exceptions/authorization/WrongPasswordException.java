package exceptions.authorization;

public class WrongPasswordException extends AuthorizeException {
    public WrongPasswordException(String message) {
        super(message);
    }
}
