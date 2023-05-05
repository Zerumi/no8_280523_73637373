package clientLogic;

import authorization.AuthorizedUserData;
import databaseUsersLogic.DBUserManager;
import databaseUsersLogic.PasswordEncryptionImplSHA512;
import exceptions.AuthorizationException;
import exceptions.AuthorizeException;
import exceptions.RegistrationFailedException;
import exceptions.UnauthorizedException;
import requestLogic.CallerBack;
import requests.authCredentials.AuthenticationData;
import requests.authCredentials.RegistrationData;

import java.io.IOException;
import java.sql.SQLException;

public class AuthorizeManager {
    public static AuthorizedUserData register(CallerBack requester, RegistrationData regData) throws AuthorizeException {
        AuthorizedUserData userData;
        try (DBUserManager manager = new DBUserManager(new PasswordEncryptionImplSHA512())) {
            userData = manager.addUserToDatabase(requester, regData);
        } catch (SQLException | RegistrationFailedException | IOException e) {
            throw new AuthorizeException(e);
        }
        return userData;
    }

    public static AuthorizedUserData authorize(CallerBack requester, AuthenticationData regData) throws AuthorizeException {
        AuthorizedUserData userData;
        try (DBUserManager manager = new DBUserManager(new PasswordEncryptionImplSHA512())) {
            userData = manager.getUserFromDatabase(regData);
            AuthorizedCallerBack callerBack = new AuthorizedCallerBack(userData, requester);
            SessionHandler.getInstance().registerSession(callerBack);
        } catch (SQLException | AuthorizationException | IOException e) {
            throw new AuthorizeException(e);
        }
        return userData;
    }

    public static AuthorizedCallerBack login(CallerBack requester, AuthenticationData regData) throws UnauthorizedException {
        return SessionHandler.getInstance().getSession(requester).getAuthorizedCallerBack();
    }
}
