package gui.controller.visualization.component;

import gui.controller.visualization.component.callback.VisualisationCallback;
import listen.logic.ServerListener;
import model.collection.actions.AddCollectionAction;
import model.collection.actions.RemoveCollectionAction;
import model.collection.actions.UpdateCollectionAction;
import response.BaseResponse;
import response.CollectionUpdatedResponse;
import response.logic.ApplicationResponseProvider;

public class VisualizationCollectionUpdatedController implements ApplicationResponseProvider<BaseResponse> {

    private final VisualisationCallback callback;

    public VisualizationCollectionUpdatedController(VisualisationCallback callback) {
        this.callback = callback;
        new ServerListener<>(CollectionUpdatedResponse.class).addListeners(this).startListen();
    }

    @Override
    public void acceptException(Exception e) {
        callback.acceptException(e);
    }

    @Override
    public void acceptResponse(BaseResponse response) {
        CollectionUpdatedResponse responseToWork = (CollectionUpdatedResponse) response;
        switch (responseToWork.getAction().getAction()) {
            case ADD -> callback.fireNewRoutes((AddCollectionAction) responseToWork.getAction());
            case REMOVE -> callback.fireRemoveRoutes((RemoveCollectionAction) responseToWork.getAction());
            case UPDATE -> callback.fireUpdateRoutes((UpdateCollectionAction) responseToWork.getAction());
        }
    }
}
