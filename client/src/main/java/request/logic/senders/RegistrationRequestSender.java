package request.logic.senders;

import authorization.credentials.RegistrationData;
import requests.RegistrationRequest;
import response.logic.ApplicationResponseProvider;
import responses.AuthorizeResponse;

import java.util.Arrays;

public class RegistrationRequestSender implements ApplicationResponseProvider<AuthorizeResponse> {

    ApplicationResponseProvider<AuthorizeResponse>[] providers;

    @SafeVarargs
    public final void sendRegisterRequest(RegistrationData data, ApplicationResponseProvider<AuthorizeResponse>... providers) {
        this.providers = providers;
        RegistrationRequest request = new RegistrationRequest(data);
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
