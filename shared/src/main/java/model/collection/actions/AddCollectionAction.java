package model.collection.actions;

import model.Route;

import java.util.Collection;

public class AddCollectionAction extends AbstractCollectionAction {

    private final Collection<? extends Route> newElements;

    public AddCollectionAction(Collection<? extends Route> newElements) {
        super(CollectionActions.ADD);
        this.newElements = newElements;
    }

    public Collection<? extends Route> getNewElements() {
        return newElements;
    }
}
