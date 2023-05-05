package clientLogic;

public class Session {
    public static final int TIMEOUT = 600000;
    private static long nextSessionId = 0L;
    private final long sessionId;
    private final AuthorizedCallerBack callerBack;

    public Session(AuthorizedCallerBack callerBack) {
        sessionId = nextSessionId++;
        this.callerBack = callerBack;
    }

    public static long getSessionCount() {
        return nextSessionId;
    }

    public long getSessionId() {
        return sessionId;
    }

    public AuthorizedCallerBack getAuthorizedCallerBack() {
        return callerBack;
    }
}
