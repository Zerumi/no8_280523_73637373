package authorization.authCredentials;

public class AuthenticationData {
    private final String username;
    private final char[] password;

    public AuthenticationData(String username, char[] password) {
        this.username = username;
        this.password = password;
    }

    public String getLogin() {
        return username;
    }

    public char[] getPassword() {
        return password;
    }
}
