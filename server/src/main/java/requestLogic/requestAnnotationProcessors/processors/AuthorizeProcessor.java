package requestLogic.requestAnnotationProcessors.processors;

import clientLogic.AuthorizeManager;
import clientLogic.AuthorizedCallerBack;
import exceptions.CannotProceedException;
import exceptions.authorizationExceptions.UnauthorizedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.requests.ServerRequest;

public class AuthorizeProcessor implements RequestAnnotationProcessor {
    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab7");
    @Override
    public ServerRequest proceedRequest(ServerRequest requestToProceed) throws CannotProceedException {
        try {
            AuthorizedCallerBack authorizedCallerBack = AuthorizeManager.login(requestToProceed.getFrom());
            logger.info("Successfully authorized " + authorizedCallerBack.getUserData().getLogin());
            return new ServerRequest(requestToProceed.getUserRequest(),
                    authorizedCallerBack,
                    requestToProceed.getConnection());
        } catch (UnauthorizedException e) {
            throw new CannotProceedException(e);
        }
    }
}
