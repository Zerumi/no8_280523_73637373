package request.logic.workers;

import models.handlers.RoutesHandler;
import request.logic.requests.ServerRequest;
import response.logic.senders.ResponseSender;
import responses.ShowCollectionResponse;

public class ShowCollectionRequestWorker implements RequestWorker {
    @Override
    public void workWithRequest(ServerRequest request) {
        ShowCollectionResponse response = new ShowCollectionResponse(RoutesHandler.getInstance().getCollection());
        ResponseSender.sendResponse(response, request.getConnection(), request.getFrom());
    }
}
