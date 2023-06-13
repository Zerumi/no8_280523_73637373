package request.logic.worker;

import authorization.AuthorizedUserData;
import client.logic.AuthorizeManager;
import exception.authorization.AuthorizeException;
import request.logic.request.ServerRequest;
import request.RegistrationRequest;
import response.logic.sender.ResponseSender;
import response.AuthorizeResponse;
import response.BaseResponse;
import response.ErrorResponse;

public class RegistrationRequestWorker implements RequestWorker {
    @Override
    public void workWithRequest(ServerRequest request) {
        RegistrationRequest requestToWork = (RegistrationRequest) request.getUserRequest();
        BaseResponse response = null;
        try {
            AuthorizedUserData userData = AuthorizeManager.register(request.getFrom(), requestToWork.getRegData());
            response = new AuthorizeResponse(userData);
        } catch (AuthorizeException e) {
            response = new ErrorResponse(e.getMessage());
        } finally {
            ResponseSender.sendResponse(response, request.getConnection(), request.getFrom());
        }
    }
}
