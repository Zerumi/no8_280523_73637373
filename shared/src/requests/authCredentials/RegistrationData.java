package requests.authCredentials;

public class RegistrationData extends AuthenticationData {

    private final String name;

    public RegistrationData(String name, String login, char[] password) {
        super(login, password);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
