package exception;

public class CommandInterruptedException extends RuntimeException {
    public CommandInterruptedException(Exception cause)
    {
        super(cause);
    }
}
