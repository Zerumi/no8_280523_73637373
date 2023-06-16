package request.logic.worker;

import authorization.AuthorizedUserData;
import client.logic.AuthorizeManager;
import exception.authorization.AuthorizeException;
import exception.authorization.NotEnoughPassLengthException;
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
        } catch (NotEnoughPassLengthException e) {
            response = new ErrorResponse("not_enough_passlen", String.valueOf(e.getEnoughLen()));
        } catch (AuthorizeException e) {
            response = new ErrorResponse("register_error", e.getMessage());
        } finally {
            ResponseSender.sendResponse(response, request.getConnection(), request.getFrom());
        }
    }
}
