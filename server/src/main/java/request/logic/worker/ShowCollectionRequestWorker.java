package request.logic.worker;

import database.logic.user.DBUserTableHandler;
import model.handler.RoutesHandler;
import request.logic.request.ServerRequest;
import response.ErrorResponse;
import response.ShowCollectionResponse;
import response.logic.sender.ResponseSender;

import java.io.IOException;
import java.sql.SQLException;

public class ShowCollectionRequestWorker implements RequestWorker {
    @Override
    public void workWithRequest(ServerRequest request) {
        try (DBUserTableHandler handler = new DBUserTableHandler()) {
            ShowCollectionResponse response = new ShowCollectionResponse(RoutesHandler.getInstance().getCollection(), handler.getOwnership());
            ResponseSender.sendResponse(response, request.getConnection(), request.getFrom());
        } catch (SQLException | IOException e) {
            ResponseSender.sendResponse(new ErrorResponse("cannot_proceed", "something went wrong.."), request.getConnection(), request.getFrom());
        }
    }
}
