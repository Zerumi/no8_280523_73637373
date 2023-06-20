package listen.provider;

import database.logic.user.DBUserTableHandler;
import model.Route;
import model.RouteFields;
import model.collection.actions.AbstractCollectionAction;
import model.collection.actions.AddCollectionAction;
import model.collection.actions.RemoveCollectionAction;
import model.collection.actions.UpdateCollectionAction;
import model.handler.CollectionUpdateListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.logic.worker.ListenCollectionChangeHubWorker;
import response.CollectionUpdatedResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;

public class CollectionActionsListener implements CollectionUpdateListener<Route> {
    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab6");
    @Override
    public void listenAdd(Collection<? extends Route> addedElements) {
        try (DBUserTableHandler handler = new DBUserTableHandler()) {
            HashMap<Long, Long> newOwners = new HashMap<>();
            for (var elem : addedElements) {
                newOwners.put(elem.getId(), handler.getOwnership(elem.getId()));
            }
            AddCollectionAction action = new AddCollectionAction(addedElements, newOwners);
            sendNotification(action);
        } catch (SQLException | IOException e) {
            logger.error("wha?", e);
        }
    }

    @Override
    public void listenUpdate(long elementId, RouteFields updatedField, Object updatedValue) {
        UpdateCollectionAction action = new UpdateCollectionAction(elementId, updatedField, updatedValue);
        sendNotification(action);
    }

    @Override
    public void listenRemove(Long[] removeIds) {
        RemoveCollectionAction action = new RemoveCollectionAction(removeIds);
        sendNotification(action);
    }

    private void sendNotification(AbstractCollectionAction action) {
        CollectionUpdatedResponse response = new CollectionUpdatedResponse(action);
        ListenCollectionChangeHubWorker.sendToAllCallers(response);
    }
}
