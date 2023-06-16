package request.logic.worker;

import exception.CannotProceedException;
import exception.UnsupportedRequestException;
import exception.authorization.UnauthorizedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.logic.request.annotation.AnnotationProcessor;
import request.logic.request.ServerRequest;
import request.*;
import response.logic.sender.ResponseSender;
import response.ErrorResponse;

import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RequestWorkerManager {

    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");
    private static final Executor handleRqMtExecutor = Executors.newWorkStealingPool();
    private final LinkedHashMap<Class<?>, RequestWorker> workers = new LinkedHashMap<>();

    public RequestWorkerManager() {
        workers.put(BaseRequest.class, new BaseRequestWorker());
        workers.put(CommandClientRequest.class, new CommandClientRequestWorker());
        workers.put(ArgumentCommandClientRequest.class, new ArgumentCommandClientRequestWorker<>());
        workers.put(CommandDescriptionsRequest.class, new CommandConfigureRequestWorker());
        workers.put(AuthorizationRequest.class, new AuthorizationRequestWorker());
        workers.put(RegistrationRequest.class, new RegistrationRequestWorker());
        workers.put(ShowCollectionRequest.class, new ShowCollectionRequestWorker());
        workers.put(ListenCollectionActionsRequest.class, new ListenCollectionChangeHubWorker());
        workers.put(UnsubscribeListenCollectionActionsRequest.class, new UnsubscribeFromListenCollectionActionsWorker());
        workers.put(UpdateSingleFieldRequest.class, new UpdateSingleFieldRequestWorker());
    }

    public void workWithRequest(ServerRequest request) {
        handleRqMtExecutor.execute(() -> {
            var finalRequest = request;
            try {
                finalRequest = new AnnotationProcessor(finalRequest).proceedAnnotations();
                RequestWorker requestWorker = Optional.ofNullable(workers.get(finalRequest.getUserRequest().getClass())).orElseThrow(() -> new UnsupportedRequestException("Указанный запрос не может быть обработан"));
                requestWorker.workWithRequest(finalRequest);
            } catch (UnsupportedRequestException e) {
                ErrorResponse response = new ErrorResponse("unknown_request_class", "Server understood Request class, but didn't know how to handle it.");
                ResponseSender.sendResponse(response, finalRequest.getConnection(), finalRequest.getFrom());
                logger.error("Got an invalid request.", e);
            } catch (CannotProceedException e) {
                ErrorResponse response;
                if (e.getCause() instanceof UnauthorizedException)
                    response = new ErrorResponse("unauthorized", "Request can't be proceed / passed some checks: " + e.getMessage());
                else
                    response = new ErrorResponse("cannot_proceed", "Request can't be proceed / passed some checks: " + e.getMessage());
                ResponseSender.sendResponse(response, finalRequest.getConnection(), finalRequest.getFrom());
                logger.error("Request can't be proceed / passed some checks.", e);
            }
        });
    }
}
