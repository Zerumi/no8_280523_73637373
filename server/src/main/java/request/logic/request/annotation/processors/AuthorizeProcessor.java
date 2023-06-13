package request.logic.request.annotation.processors;

import client.logic.AuthorizeManager;
import client.logic.AuthorizedCallerBack;
import exception.CannotProceedException;
import exception.authorization.UnauthorizedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.logic.request.ServerRequest;

public class AuthorizeProcessor implements RequestAnnotationProcessor {
    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab7");
    @Override
    public ServerRequest proceedRequest(ServerRequest requestToProceed) throws CannotProceedException {
        try {
            AuthorizedCallerBack authorizedCallerBack = AuthorizeManager.login(requestToProceed.getFrom());
            logger.info("Successfully authorized " + authorizedCallerBack.getUserData().login());
            return new ServerRequest(requestToProceed.getUserRequest(),
                    authorizedCallerBack,
                    requestToProceed.getConnection());
        } catch (UnauthorizedException e) {
            throw new CannotProceedException(e);
        }
    }
}
