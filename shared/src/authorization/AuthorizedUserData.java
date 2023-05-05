package authorization;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class AuthorizedUserData implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorizedUserData userData = (AuthorizedUserData) o;
        return userID == userData.userID && Objects.equals(name, userData.name) && Objects.equals(login, userData.login) && Objects.equals(lastLogin, userData.lastLogin) && Objects.equals(registerIP, userData.registerIP) && Objects.equals(registerDate, userData.registerDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, name, login, lastLogin, registerIP, registerDate);
    }

    @Override
    public String toString() {
        return "AuthorizedUserData{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", lastLogin=" + lastLogin +
                ", registerIP='" + registerIP + '\'' +
                ", registerDate=" + registerDate +
                '}';
    }
}
