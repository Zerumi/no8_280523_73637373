package authentication;

import authorization.credential.AuthenticationData;
import request.logic.sender.AuthorizationRequestSender;
import response.logic.ApplicationResponseProvider;
import response.AuthorizeResponse;

import java.util.Arrays;

public class AuthLogic implements ApplicationResponseProvider<AuthorizeResponse> {

    ApplicationResponseProvider<AuthorizeResponse>[] providers;

    @SafeVarargs
    public final void auth(String username, char[] password, ApplicationResponseProvider<AuthorizeResponse>... providers) {
        this.providers = providers;
        AuthenticationData data = new AuthenticationData(username, password);
        new AuthorizationRequestSender().sendLoginRequest(data, this);
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
