package exceptions;

public class GotAnErrorResponseException extends Exception {

    public GotAnErrorResponseException(String msg) {
        super(msg);
    }
}
