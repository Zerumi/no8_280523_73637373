package models.handlers;

import models.Route;
import models.comparators.RouteComparator;
import multiThreadLogic.CollectinonSyncronize;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

/**
 * Current implementation of CollectionsHandler for HashSet of Route.
 *
 * @author Zerumi
 * @since 1.0
 */
public class RoutesHandler implements CollectionHandler<HashSet<Route>, Route> {

    private static RoutesHandler singletoneMoment;
    private final Date initDate;
    private HashSet<Route> routes;
    private final Lock readLock;
    private final Lock writeLock;

    private RoutesHandler() {
        routes = new HashSet<>();
        initDate = Date.from(Instant.now());
        readLock = CollectinonSyncronize.getInstance().getLock().readLock();
        writeLock = CollectinonSyncronize.getInstance().getLock().writeLock();
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
        this.routes = routes;
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
        sort();
        writeLock.unlock();
    }

    /**
     * Sorts elements by ID Field in Route.
     */
    @Override
    public void sort() {
        writeLock.lock();
        HashSet<Route> sorted = new HashSet<>();

        for (Iterator<Route> it = routes.stream().sorted(new RouteComparator()).iterator(); it.hasNext(); ) {
            Route sortedItem = it.next();

            sorted.add(sortedItem);
        }

        this.routes = sorted;
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
}
