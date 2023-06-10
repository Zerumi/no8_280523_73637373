package request.logic.workers;

import authorization.AuthorizedUserData;
import client.logic.AuthorizeManager;
import exceptions.authorization.AuthorizeException;
import request.logic.requests.ServerRequest;
import requests.AuthorizationRequest;
import response.logic.senders.ResponseSender;
import responses.AuthorizeResponse;
import responses.BaseResponse;
import responses.ErrorResponse;

public class AuthorizationRequestWorker implements RequestWorker {
    @Override
    public void workWithRequest(ServerRequest request) {
        AuthorizationRequest requestToWork = (AuthorizationRequest) request.getUserRequest();
        BaseResponse response = null;
        try {
            AuthorizedUserData authorizedUser = AuthorizeManager.authorize(request.getFrom(), requestToWork.getAuthenticationData());
            response = new AuthorizeResponse(authorizedUser);
        } catch (AuthorizeException e) {
            response = new ErrorResponse(e.getMessage());
        } finally {
            ResponseSender.sendResponse(response, request.getConnection(), request.getFrom());
        }
    }
}
