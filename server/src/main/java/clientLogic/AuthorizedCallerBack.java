package clientLogic;

import authorization.AuthorizedUserData;
import requestLogic.CallerBack;

public class AuthorizedCallerBack extends CallerBack {
    private final AuthorizedUserData userData;

    private final CallerBack callerBack;

    public AuthorizedCallerBack(AuthorizedUserData userData, CallerBack callerBack) {
        super(callerBack.getAddress(), callerBack.getPort());
        this.userData = userData;
        this.callerBack = callerBack;
    }

    public CallerBack getCallerBack() {
        return callerBack;
    }

    public AuthorizedUserData getUserData() {
        return userData;
    }
}
