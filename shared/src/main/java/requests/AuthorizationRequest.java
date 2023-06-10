package requests;

import authorization.credentials.AuthenticationData;

public class AuthorizationRequest extends BaseRequest {
    private final AuthenticationData authenticationData;

    public AuthorizationRequest(AuthenticationData authenticationData) {
        this.authenticationData = authenticationData;
    }

    public AuthenticationData getAuthenticationData() {
        return authenticationData;
    }
}
