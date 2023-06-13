package listen.provider;

import model.Route;
import model.RouteFields;
import model.collection.actions.AbstractCollectionAction;
import model.collection.actions.AddCollectionAction;
import model.collection.actions.RemoveCollectionAction;
import model.collection.actions.UpdateCollectionAction;
import model.handler.CollectionUpdateListener;
import request.logic.worker.ListenCollectionChangeHubWorker;
import response.CollectionUpdatedResponse;

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
