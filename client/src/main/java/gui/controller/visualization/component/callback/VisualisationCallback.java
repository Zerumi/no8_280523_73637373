package gui.controller.visualization.component.callback;

import core.provider.ExceptionProvider;
import model.collection.actions.AddCollectionAction;
import model.collection.actions.RemoveCollectionAction;
import model.collection.actions.UpdateCollectionAction;

public interface VisualisationCallback extends ExceptionProvider {
    void fireNewRoutes(AddCollectionAction action);

    void fireUpdateRoutes(UpdateCollectionAction action);

    void fireRemoveRoutes(RemoveCollectionAction action);
}
