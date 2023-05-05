package authorization;

import java.time.LocalDate;

public class AuthorizedUserData {
    private final long userID;
    private final String name;
    private final String login;
    private final LocalDate lastLogin;
    private final String registerIP;
    private final LocalDate registerDate;

    public AuthorizedUserData(long userID, String name, String login, LocalDate lastLogin, String registerIP, LocalDate registerDate) {
        this.userID = userID;
        this.name = name;
        this.login = login;
        this.lastLogin = lastLogin;
        this.registerIP = registerIP;
        this.registerDate = registerDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getLastLogin() {
        return lastLogin;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public long getUserID() {
        return userID;
    }

    public String getLogin() {
        return login;
    }

    public String getRegisterIP() {
        return registerIP;
    }
}
