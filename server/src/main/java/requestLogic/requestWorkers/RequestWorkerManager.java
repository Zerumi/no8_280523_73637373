package requestLogic.requestWorkers;

import exceptions.CannotProceedException;
import exceptions.UnsupportedRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.requestAnnotationProcessors.AnnotationProcessor;
import requestLogic.requests.ServerRequest;
import requests.*;
import responseLogic.responseSenders.SuppressIOResponseSender;
import responses.ErrorResponse;

import java.util.LinkedHashMap;
import java.util.Optional;

public class RequestWorkerManager {

    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");

    private final LinkedHashMap<Class<?>, RequestWorker> workers = new LinkedHashMap<>();

    public RequestWorkerManager() {
        workers.put(BaseRequest.class, new BaseRequestWorker());
        workers.put(CommandClientRequest.class, new CommandClientRequestWorker());
        workers.put(ArgumentCommandClientRequest.class, new ArgumentCommandClientRequestWorker<>());
        workers.put(CommandDescriptionsRequest.class, new CommandConfigureRequestWorker());
        workers.put(AuthorizationRequest.class, new AuthorizationRequestWorker());
        workers.put(RegistrationRequest.class, new RegistrationRequestWorker());
    }

    public void workWithRequest(ServerRequest request) {
        try {
            request = new AnnotationProcessor(request).proceedAnnotations();
            RequestWorker requestWorker = Optional.ofNullable(workers.get(request.getUserRequest().getClass())).orElseThrow(()
                    -> new UnsupportedRequestException("Указанный запрос не может быть обработан"));
            requestWorker.workWithRequest(request);
        } catch (UnsupportedRequestException e) {
            ErrorResponse response = new ErrorResponse("Server understood Request class, but didn't know how to handle it.");
            SuppressIOResponseSender.sendResponse(response, request.getConnection(), request.getFrom());
            logger.error("Got an invalid request.", e);
        } catch (CannotProceedException e) {
            ErrorResponse response = new ErrorResponse("Request can't be proceed / passed some checks: " + e.getMessage());
            SuppressIOResponseSender.sendResponse(response, request.getConnection(), request.getFrom());
            logger.error("Request can't be proceed / passed some checks.", e);
        }
    }
}
