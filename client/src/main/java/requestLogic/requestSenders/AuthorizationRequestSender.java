package requestLogic.requestSenders;

import authorization.authCredentials.AuthenticationData;
import requests.AuthorizationRequest;
import responseLogic.ApplicationResponseProvider;
import responses.AuthorizeResponse;

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
