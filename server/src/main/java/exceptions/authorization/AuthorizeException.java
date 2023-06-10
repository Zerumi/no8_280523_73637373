package exceptions.authorization;

public class AuthorizeException extends Exception {
    public AuthorizeException(Exception e) {
        super(e);
    }

    public AuthorizeException(String message) {
        super(message);
    }
}
