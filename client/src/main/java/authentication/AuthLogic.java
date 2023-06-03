package authentication;

import authorization.authCredentials.AuthenticationData;
import requestLogic.requestSenders.AuthorizationRequestSender;
import responseLogic.ApplicationResponseProvider;
import responses.AuthorizeResponse;

import java.util.Arrays;

public class AuthLogic implements ApplicationResponseProvider<AuthorizeResponse> {

    ApplicationResponseProvider<AuthorizeResponse>[] providers;

    @SafeVarargs
    public final void auth(String username, char[] password, ApplicationResponseProvider<AuthorizeResponse>... providers) {
        this.providers = providers;
        AuthenticationData data = new AuthenticationData(username, password);
        new AuthorizationRequestSender().sendLoginRequest(data);
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
