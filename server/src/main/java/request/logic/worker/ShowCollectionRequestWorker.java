package request.logic.worker;

import model.handler.RoutesHandler;
import request.logic.request.ServerRequest;
import response.logic.sender.ResponseSender;
import response.ShowCollectionResponse;

public class ShowCollectionRequestWorker implements RequestWorker {
    @Override
    public void workWithRequest(ServerRequest request) {
        ShowCollectionResponse response = new ShowCollectionResponse(RoutesHandler.getInstance().getCollection());
        ResponseSender.sendResponse(response, request.getConnection(), request.getFrom());
    }
}
