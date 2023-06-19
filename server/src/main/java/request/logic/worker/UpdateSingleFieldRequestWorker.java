package request.logic.worker;

import client.logic.AuthorizedCallerBack;
import database.logic.element.DBIntegrationUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.UpdateSingleFieldRequest;
import request.logic.request.ServerRequest;
import response.ErrorResponse;
import response.logic.sender.ResponseSender;

public class UpdateSingleFieldRequestWorker implements RequestWorker {

    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab8");

    @Override
    public void workWithRequest(ServerRequest request) {
        UpdateSingleFieldRequest requestToWork = (UpdateSingleFieldRequest) request.getUserRequest();

        logger.info("working with update single element...");

        var response = DBIntegrationUtility.getInstance().updateSingleField(
                ((AuthorizedCallerBack) request.getFrom()).getUserData().userID(),
                requestToWork.getObjId(),
                requestToWork.getField(),
                requestToWork.getValueToSet());

        if (response.code() == 403) {
            ResponseSender.sendResponse(new ErrorResponse("inaccessible", "can't access element"), request.getConnection(), request.getFrom());
        }
    }
}
