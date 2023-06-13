package request.logic.worker;

import client.logic.AuthorizedCallerBack;
import database.logic.element.DBIntegrationUtility;
import model.Route;
import model.handler.CollectionHandler;
import model.handler.RoutesHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.logic.request.ServerRequest;
import request.UpdateSingleFieldRequest;

import java.util.HashSet;
import java.util.Optional;

public class UpdateSingleFieldRequestWorker implements RequestWorker {

    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab8");

    @Override
    public void workWithRequest(ServerRequest request) {
        UpdateSingleFieldRequest requestToWork = (UpdateSingleFieldRequest) request.getUserRequest();

        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();

        Optional<Route> changedObj = collectionHandler
                .getCollection()
                .stream()
                .filter(x ->
                        x.getId().equals(requestToWork.getObjId()))
                .findAny();

        logger.info("working with update single element...");

        DBIntegrationUtility.getInstance().updateSingleField(
                ((AuthorizedCallerBack) request.getFrom()).getUserData().userID(),
                requestToWork.getObjId(),
                requestToWork.getField(),
                requestToWork.getValueToSet());
    }
}
