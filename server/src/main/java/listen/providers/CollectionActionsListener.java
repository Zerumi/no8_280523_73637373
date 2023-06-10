package listen.providers;

import models.Route;
import models.RouteFields;
import models.collection.actions.AbstractCollectionAction;
import models.collection.actions.AddCollectionAction;
import models.collection.actions.RemoveCollectionAction;
import models.collection.actions.UpdateCollectionAction;
import models.handlers.CollectionUpdateListener;
import request.logic.workers.ListenCollectionChangeHubWorker;
import responses.CollectionUpdatedResponse;

import java.util.Collection;

public class CollectionActionsListener implements CollectionUpdateListener<Route> {
    @Override
    public void listenAdd(Collection<? extends Route> addedElements) {
        AddCollectionAction action = new AddCollectionAction(addedElements);
        sendNotification(action);
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
