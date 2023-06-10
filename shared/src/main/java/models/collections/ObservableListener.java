package models.collections;

import java.util.Collection;

public interface ObservableListener<E> {
    void listenAdd(Collection<? extends E> addedElements);

    void listenRemove(Collection<?> removedObjects);
}
