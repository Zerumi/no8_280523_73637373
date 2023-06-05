package models.collection.actions;

import models.RouteFields;

public class UpdateCollectionAction extends AbstractCollectionAction {
    private final long elementId;
    private final RouteFields updatedFiled;
    private final Object updatedValue;

    public UpdateCollectionAction(long elementId, RouteFields updatedField, Object updatedValue) {
        super(CollectionActions.UPDATE);
        this.elementId = elementId;
        this.updatedFiled = updatedField;
        this.updatedValue = updatedValue;
    }

    public long getElementId() {
        return elementId;
    }

    public RouteFields getUpdatedFiled() {
        return updatedFiled;
    }

    public Object getUpdatedValue() {
        return updatedValue;
    }
}
