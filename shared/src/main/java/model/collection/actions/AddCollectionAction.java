package model.collection.actions;

import model.Route;

import java.util.Collection;
import java.util.HashMap;

public class AddCollectionAction extends AbstractCollectionAction {

    private final Collection<? extends Route> newElements;
    private final HashMap<Long, Long> newOwners;

    public AddCollectionAction(Collection<? extends Route> newElements, HashMap<Long, Long> newOwners) {
        super(CollectionActions.ADD);
        this.newElements = newElements;
        this.newOwners = newOwners;
    }

    public Collection<? extends Route> getNewElements() {
        return newElements;
    }

    public HashMap<Long, Long> getNewOwners() {
        return newOwners;
    }
}
