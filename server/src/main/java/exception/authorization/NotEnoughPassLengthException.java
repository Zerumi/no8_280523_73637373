package exception.authorization;

public class NotEnoughPassLengthException extends RegistrationFailedException {

    private final int enoughLen;

    public NotEnoughPassLengthException(int enoughLen) {
        super("not enough pass " + enoughLen + " expected chars");

        this.enoughLen = enoughLen;
    }

    public int getEnoughLen() {
        return enoughLen;
    }
}
