package model.collection.actions;

public class RemoveCollectionAction extends AbstractCollectionAction {

    private final Long[] removed_ids;

    public RemoveCollectionAction(Long[] removed_ids) {
        super(CollectionActions.REMOVE);
        this.removed_ids = removed_ids;
    }

    public Long[] getRemoved_ids() {
        return removed_ids;
    }
}
