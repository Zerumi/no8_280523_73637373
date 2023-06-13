package model.handler;

import model.Route;
import model.collections.ObservableHashSet;
import model.collections.ObservableListener;
import model.comparator.RouteComparator;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

/**
 * Current implementation of CollectionsHandler for HashSet of Route.
 *
 * @author Zerumi
 * @since 1.0
 */
public class RoutesHandler implements CollectionHandler<HashSet<Route>, Route>, ObservableListener<Route> {

    private static RoutesHandler singletoneMoment;
    private final Date initDate;
    private HashSet<Route> routes;
    private final Lock readLock;
    private final Lock writeLock;
    private final ArrayList<CollectionUpdateListener<Route>> listeners;

    private RoutesHandler() {
        ObservableHashSet<Route> observableHashSet = new ObservableHashSet<>();
        observableHashSet.registerListener(this);
        this.routes = observableHashSet;
        initDate = Date.from(Instant.now());
        ReadWriteLock rwl = new ReentrantReadWriteLock();
        readLock = rwl.readLock();
        writeLock = rwl.writeLock();
        listeners = new ArrayList<>();
    }

    /**
     * Singletone moment.
     *
     * @return Single instance of handler.
     */
    public static RoutesHandler getInstance() {
        if (singletoneMoment == null)
            singletoneMoment = new RoutesHandler();
        return singletoneMoment;
    }

    /**
     * Returns actual collection reference.
     *
     * @return Current collection
     */
    @Override
    public HashSet<Route> getCollection() {
        readLock.lock();
        var result = routes;
        readLock.unlock();
        return result;
    }

    /**
     * Overrides current collection by provided value.
     *
     * @param routes Collection
     */
    @Override
    public void setCollection(HashSet<Route> routes) {
        writeLock.lock();
        ObservableHashSet<Route> observableHashSet = new ObservableHashSet<>(routes);
        observableHashSet.registerListener(this);
        this.routes = observableHashSet;
        sort();
        writeLock.unlock();
    }

    /**
     * Adds element to collection.
     *
     * @param e Element to add
     */
    @Override
    public void addElementToCollection(Route e) {
        writeLock.lock();
        routes.add(e);
        writeLock.unlock();
    }

    @Override
    public HashSet<Route> getSorted() {
        readLock.lock();
        var result = routes.stream().sorted(new RouteComparator()).collect(Collectors.toCollection(LinkedHashSet::new));
        readLock.unlock();
        return result;
    }

    /**
     * Returns first element of collection.
     *
     * @return First element of collection. If collection is empty, returns new object.
     */
    @Override
    public Route getFirstOrNew() {
        Route result;
        readLock.lock();
        if (routes.iterator().hasNext())
            result = routes.iterator().next();
        else
            result = new Route();
        readLock.unlock();
        return result;
    }

    @Override
    public Date getInitDate() {
        return initDate;
    }

    /**
     * Returns last element of collection.
     *
     * @return Last element of collection of null if collection is empty
     */
    @Override
    public Route getLastElement() {
        Route result = null;
        readLock.lock();
        for (Route route : routes) {
            result = route;
        }
        readLock.unlock();
        return result;
    }

    /**
     * Gets min element by given comparator
     *
     * @param comparator Comparator to compare.
     * @return Min element or null if collection is empty
     */
    @Override
    public Route getMin(Comparator<Route> comparator) {
        readLock.lock();
        var result = getCollection().stream().min(comparator).orElse(null);
        readLock.unlock();
        return result;
    }

    /**
     * Gets max element by given comparator
     *
     * @param comparator Comparator to compare.
     * @return Max element or null if collection is empty
     */
    @Override
    public Route getMax(Comparator<Route> comparator) {
        readLock.lock();
        var result = getCollection().stream().max(comparator).orElse(null);
        readLock.unlock();
        return result;
    }

    @Override
    public void addCollectionListener(CollectionUpdateListener<Route> listener) {
        this.listeners.add(listener);
    }

    @Override
    public void listenAdd(Collection<? extends Route> addedElements) {
        this.listeners.forEach(x -> x.listenAdd(addedElements));
    }

    @Override
    public void listenRemove(Collection<?> removedObjects) {
        ArrayList<Long> totalIds = new ArrayList<>();
        for (var removedObj : removedObjects) {
            if (removedObj.getClass().equals(Route.class)) {
                totalIds.add(((Route) removedObj).getId());
            }
        }
        this.listeners.forEach(x -> x.listenRemove(totalIds.toArray(new Long[0])));
    }

    public ArrayList<CollectionUpdateListener<Route>> getListeners() {
        return listeners;
    }
}
