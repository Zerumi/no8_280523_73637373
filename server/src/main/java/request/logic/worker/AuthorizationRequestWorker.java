package request.logic.worker;

import authorization.AuthorizedUserData;
import client.logic.AuthorizeManager;
import exception.authorization.AuthorizeException;
import request.logic.request.ServerRequest;
import request.AuthorizationRequest;
import response.logic.sender.ResponseSender;
import response.AuthorizeResponse;
import response.BaseResponse;
import response.ErrorResponse;

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
