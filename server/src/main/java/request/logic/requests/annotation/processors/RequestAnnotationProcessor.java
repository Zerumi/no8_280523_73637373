package request.logic.requests.annotation.processors;

import exceptions.CannotProceedException;
import request.logic.requests.ServerRequest;

public interface RequestAnnotationProcessor {
    ServerRequest proceedRequest(ServerRequest requestToProceed) throws CannotProceedException;
}
