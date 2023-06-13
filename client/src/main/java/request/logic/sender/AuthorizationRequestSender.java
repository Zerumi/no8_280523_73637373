package request.logic.sender;

import authorization.credential.AuthenticationData;
import request.AuthorizationRequest;
import response.logic.ApplicationResponseProvider;
import response.AuthorizeResponse;

import java.util.Arrays;

public class AuthorizationRequestSender implements ApplicationResponseProvider<AuthorizeResponse> {

    ApplicationResponseProvider<AuthorizeResponse>[] providers;

    @SafeVarargs
    public final void sendLoginRequest(AuthenticationData data, ApplicationResponseProvider<AuthorizeResponse>... providers) {
        this.providers = providers;
        AuthorizationRequest request = new AuthorizationRequest(data);
        new LoginRequestSender().sendLoginRequest(request, this);
    }

    @Override
    public void acceptException(Exception e) {
        Arrays.stream(providers).forEach(x -> x.acceptException(e));
    }

    @Override
    public void acceptResponse(AuthorizeResponse response) {
        Arrays.stream(providers).forEach(x -> x.acceptResponse(response));
    }
}
