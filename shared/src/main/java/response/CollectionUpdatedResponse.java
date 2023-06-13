package response;

import model.collection.actions.AbstractCollectionAction;

public class CollectionUpdatedResponse extends BaseResponse {
    private final AbstractCollectionAction action;

    public CollectionUpdatedResponse(AbstractCollectionAction action) {
        this.action = action;
    }

    public AbstractCollectionAction getAction() {
        return action;
    }
}
