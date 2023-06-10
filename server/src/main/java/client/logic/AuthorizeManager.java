package client.logic;

import authorization.AuthorizedUserData;
import authorization.credentials.AuthenticationData;
import authorization.credentials.RegistrationData;
import database.logic.user.DBUserManager;
import database.logic.user.PasswordEncryptionImplSHA512;
import exceptions.authorization.AuthorizeException;
import exceptions.authorization.UnauthorizedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.logic.CallerBack;

import java.io.IOException;
import java.sql.SQLException;

public class AuthorizeManager {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");
    public static AuthorizedUserData register(CallerBack requester, RegistrationData regData) throws AuthorizeException {
        AuthorizedUserData userData;
        try (DBUserManager manager = new DBUserManager(new PasswordEncryptionImplSHA512())) {
            if (manager.checkExistence(regData.getLogin()))
                throw new AuthorizeException("User with that login already exists.");
            userData = manager.addUserToDatabase(requester, regData);
            AuthorizedCallerBack callerBack = new AuthorizedCallerBack(userData, requester);
            SessionHandler.getInstance().registerSession(callerBack);
        } catch (SQLException | IOException e) {
            logger.error(e);
            throw new AuthorizeException("Сервер авторизации недоступен или завершил запрос с ошибкой. " +
                    "Обратитесь к администратору сервера");
        }
        return userData;
    }

    public static AuthorizedUserData authorize(CallerBack requester, AuthenticationData regData) throws AuthorizeException {
        AuthorizedUserData userData;
        try (DBUserManager manager = new DBUserManager(new PasswordEncryptionImplSHA512())) {
            userData = manager.getUserFromDatabase(regData);
            AuthorizedCallerBack callerBack = new AuthorizedCallerBack(userData, requester);
            SessionHandler.getInstance().registerSession(callerBack);
        } catch (SQLException | IOException e) {
            logger.error(e);
            throw new AuthorizeException("Сервер авторизации недоступен или завершил запрос с ошибкой. " +
                    "Обратитесь к администратору сервера");
        }
        return userData;
    }

    public static AuthorizedCallerBack login(CallerBack requester) throws UnauthorizedException {
        return SessionHandler.getInstance().getSession(requester).getAuthorizedCallerBack();
    }
}
