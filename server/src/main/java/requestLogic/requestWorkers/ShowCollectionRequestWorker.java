package requestLogic.requestWorkers;

import models.handlers.RoutesHandler;
import requestLogic.requests.ServerRequest;
import responseLogic.responseSenders.ResponseSender;
import responses.ShowCollectionResponse;

public class ShowCollectionRequestWorker implements RequestWorker {
    @Override
    public void workWithRequest(ServerRequest request) {
        ShowCollectionResponse response = new ShowCollectionResponse(RoutesHandler.getInstance().getCollection());
        ResponseSender.sendResponse(response, request.getConnection(), request.getFrom());
    }
}
