package request;

import authorization.credential.RegistrationData;

public class RegistrationRequest extends BaseRequest {
    private final RegistrationData data;

    public RegistrationRequest(RegistrationData data) {
        this.data = data;
    }

    public RegistrationData getRegData() {
        return data;
    }
}
