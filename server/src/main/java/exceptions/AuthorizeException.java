package exceptions;

public class AuthorizeException extends Exception {
    public AuthorizeException(Exception e) {
        super(e);
    }
}
