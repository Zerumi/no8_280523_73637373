package request.logic.request.annotation.processors;

import exception.CannotProceedException;
import request.logic.request.ServerRequest;

public interface RequestAnnotationProcessor {
    ServerRequest proceedRequest(ServerRequest requestToProceed) throws CannotProceedException;
}
