package request.logic.worker;

import authorization.AuthorizedUserData;
import client.logic.AuthorizeManager;
import exception.authorization.AuthorizeException;
import exception.authorization.UnregisteredException;
import exception.authorization.WrongPasswordException;
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
        } catch (WrongPasswordException e) {
            response = new ErrorResponse("wrong_password", e.getMessage());
        } catch (UnregisteredException e) {
            response = new ErrorResponse("unknown_login", e.getMessage());
        } catch (AuthorizeException e) {
            response = new ErrorResponse("auth_error", e.getMessage());
        } finally {
            ResponseSender.sendResponse(response, request.getConnection(), request.getFrom());
        }
    }
}
