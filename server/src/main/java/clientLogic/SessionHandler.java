package clientLogic;

import exceptions.UnauthorizedException;
import requestLogic.CallerBack;

import java.util.HashSet;

public class SessionHandler {

    private static final SessionHandler instance;

    static {
        instance = new SessionHandler();
    }

    private final HashSet<Session> sessions;

    {
        sessions = new HashSet<>();
    }

    private SessionHandler() {
    }

    public static SessionHandler getInstance() {
        return instance;
    }

    public Session getSession(CallerBack callerBack) throws UnauthorizedException {
        return sessions.stream().filter(x -> x.getAuthorizedCallerBack().getCallerBack().equals(callerBack)).findAny()
                .orElseThrow(() -> new UnauthorizedException("Client is unauthorized"));
    }

    public void registerSession(AuthorizedCallerBack callerBack) {
        sessions.add(new Session(callerBack));
    }
}
