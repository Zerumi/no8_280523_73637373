package models.handlers;

import models.RouteFields;

import java.util.Collection;

public interface CollectionUpdateListener<E> {
    void listenAdd(Collection<? extends E> addedElements);

    void listenUpdate(long elementId, RouteFields updatedField, Object updatedValue);

    void listenRemove(Long[] removeIds);
}
