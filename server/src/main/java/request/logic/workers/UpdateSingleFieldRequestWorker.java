package request.logic.workers;

import clientLogic.AuthorizedCallerBack;
import databaseLogic.databaseElementLogic.DBIntegrationUtility;
import models.Route;
import models.handlers.CollectionHandler;
import models.handlers.RoutesHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.logic.requests.ServerRequest;
import requests.UpdateSingleFieldRequest;

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
