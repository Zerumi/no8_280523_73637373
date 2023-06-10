package request.logic.workers;

import authorization.AuthorizedUserData;
import client.logic.AuthorizeManager;
import exceptions.authorization.AuthorizeException;
import request.logic.requests.ServerRequest;
import requests.RegistrationRequest;
import response.logic.senders.ResponseSender;
import responses.AuthorizeResponse;
import responses.BaseResponse;
import responses.ErrorResponse;

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
