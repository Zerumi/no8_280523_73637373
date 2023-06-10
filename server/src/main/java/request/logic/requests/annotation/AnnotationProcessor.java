package request.logic.requests.annotation;

import exceptions.CannotProceedException;
import request.logic.requests.annotation.processors.AuthorizeProcessor;
import request.logic.requests.annotation.processors.RequestAnnotationProcessor;
import request.logic.requests.ServerRequest;
import requests.annotations.Authorize;

import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;

public class AnnotationProcessor {

    private static final LinkedHashMap<Class<? extends Annotation>, RequestAnnotationProcessor> loadedAnnotations;

    static {
        loadedAnnotations = new LinkedHashMap<>();
        loadedAnnotations.put(Authorize.class, new AuthorizeProcessor());
    }

    private final ServerRequest requestToProceed;

    public AnnotationProcessor(ServerRequest requestToProceed) {
        this.requestToProceed = requestToProceed;
    }

    public ServerRequest proceedAnnotations() throws CannotProceedException {
        ServerRequest result = requestToProceed;
        for (Class<? extends Annotation> annotation : loadedAnnotations.keySet()) {
            if (requestToProceed.getUserRequest().getClass().isAnnotationPresent(annotation))
                result = loadedAnnotations.get(annotation).proceedRequest(requestToProceed);
        }
        return result;
    }
}
