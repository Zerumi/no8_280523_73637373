package request.logic.worker;

import client.logic.AuthorizedCallerBack;
import database.logic.element.DBIntegrationUtility;
import request.RemoveFromCollectionRequest;
import request.logic.request.ServerRequest;
import response.ErrorResponse;
import response.logic.sender.ResponseSender;

public class RemoveFromCollectionRequestWorker implements RequestWorker {
    @Override
    public void workWithRequest(ServerRequest request) {
        RemoveFromCollectionRequest requestToWork = (RemoveFromCollectionRequest) request.getUserRequest();
        if (!DBIntegrationUtility.getInstance().removeFromCollectionAndDB(((AuthorizedCallerBack) request.getFrom()).getUserData().userID(), requestToWork.getElementID())) {
            ResponseSender.sendResponse(new ErrorResponse("inaccessible", "can't remove"), request.getConnection(), request.getFrom());
        }
    }
}
