package exception;

public class CannotProceedException extends Throwable {
    public CannotProceedException(Exception e) {
        super(e);
    }
}
