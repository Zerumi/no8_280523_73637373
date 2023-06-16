package exception;

public class CannotProceedException extends Exception {
    public CannotProceedException(Exception e) {
        super(e);
    }
}
