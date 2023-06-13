package model.collections;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class ObservableHashSet<T> extends HashSet<T> {

    private transient final ArrayList<ObservableListener<T>> listeners;

    {
        listeners = new ArrayList<>();
    }

    public ObservableHashSet(HashSet<T> routes) {
        super(routes);
    }

    public ObservableHashSet() {

    }

    @SafeVarargs
    public final void registerListener(ObservableListener<T>... listeners) {
        this.listeners.addAll(Arrays.stream(listeners).toList());
    }

    @Override
    public boolean add(T t) {
        if (listeners != null) {
            ArrayList<T> addedObjects = new ArrayList<>();
            addedObjects.add(t);
            listeners.forEach(x -> x.listenAdd(addedObjects));
        }
        return super.add(t);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends T> c) {
        if (listeners != null)
            listeners.forEach(x -> x.listenAdd(c));
        return super.addAll(c);
    }

    @Override
    public boolean remove(Object o) {
        if (listeners != null) {
            ArrayList<Object> removedObjects = new ArrayList<>();
            removedObjects.add(o);
            listeners.forEach(x -> x.listenRemove(removedObjects));
        }
        return super.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (listeners != null)
            listeners.forEach(x -> x.listenRemove(c));
        return super.removeAll(c);
    }
}
