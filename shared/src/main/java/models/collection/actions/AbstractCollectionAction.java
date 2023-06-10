package models.collection.actions;

import java.io.Serializable;

public abstract class AbstractCollectionAction implements Serializable {
    private final CollectionActions action;

    public AbstractCollectionAction(CollectionActions action) {
        this.action = action;
    }

    public CollectionActions getAction() {
        return action;
    }
}
