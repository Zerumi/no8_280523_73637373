package requestLogic.requestAnnotationProcessors.processors;

import clientLogic.AuthorizeManager;
import exceptions.CannotProceedException;
import exceptions.authorizationExceptions.UnauthorizedException;
import requestLogic.requests.ServerRequest;

public class AuthorizeProcessor implements RequestAnnotationProcessor {
    @Override
    public ServerRequest proceedRequest(ServerRequest requestToProceed) throws CannotProceedException {
        try {
            return new ServerRequest(requestToProceed.getUserRequest(),
                    AuthorizeManager.login(requestToProceed.getFrom()),
                    requestToProceed.getConnection());
        } catch (UnauthorizedException e) {
            throw new CannotProceedException(e);
        }
    }
}
