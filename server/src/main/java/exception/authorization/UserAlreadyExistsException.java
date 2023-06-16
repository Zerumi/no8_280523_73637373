package exception.authorization;

public class UserAlreadyExistsException extends AuthorizeException {
    public UserAlreadyExistsException(String s) {
        super(s);
    }
}
