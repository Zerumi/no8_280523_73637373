package request.logic.worker;

import client.logic.AuthorizedCallerBack;
import database.logic.element.DBIntegrationUtility;
import request.UpdateElementRequest;
import request.logic.request.ServerRequest;

public class UpdateElementRequestWorker implements RequestWorker {
    @Override
    public void workWithRequest(ServerRequest request) {
        UpdateElementRequest requestToWork = (UpdateElementRequest) request.getUserRequest();

        DBIntegrationUtility.getInstance()
                .updateElementInDBAndCollection(
                        requestToWork.getNewRoute(),
                        requestToWork.getNewRoute().getId(),
                        ((AuthorizedCallerBack) request.getFrom()).getUserData().userID());
    }
}
