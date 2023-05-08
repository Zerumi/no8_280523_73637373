package databaseLogic.databaseElementLogic;

import models.Route;
import models.handlers.CollectionHandler;
import models.handlers.RoutesHandler;
import multiThreadLogic.CollectinonSyncronize;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responseLogic.StatusResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.function.Supplier;

public class DBIntegrationUtility {
    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab6");

    private static final DBIntegrationUtility instance;

    static {
        instance = new DBIntegrationUtility();
    }

    private final Lock writeLock;
    private final Lock readLock;

    {
        writeLock = CollectinonSyncronize.getInstance().getLock().writeLock();
        readLock = CollectinonSyncronize.getInstance().getLock().readLock();
    }

    public static DBIntegrationUtility getInstance() {
        return instance;
    }

    public StatusResponse addRouteToDBAndCollection(Route route, long creatorID) {
        StatusResponse response;

        writeLock.lock();
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();
        try (DBCollectionManager manager = new DBCollectionManager();
             DBElementCreatorLogic logic = new DBElementCreatorLogic()) {
            if (manager.addElementToDataBase(route)) {
                logic.addCreatorToDB(route.getId(), creatorID);
                collectionHandler.addElementToCollection(route);
                response = new StatusResponse("Element added!", 200);
            } else response = new StatusResponse("Element not added", -4);
        } catch (SQLException | IOException e) {
            response = new StatusResponse("Something went wrong during adding element. Ask server administrator for further information.", -53);
            logger.error("Something went wrong during adding element! ", e);
        } finally {
            writeLock.unlock();
        }

        return response;
    }

    public StatusResponse updateElementInDBAndCollection(Route route, long elementID, long creatorID) {
        StatusResponse response;

        writeLock.lock();
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();
        try (DBCollectionManager manager = new DBCollectionManager();
             DBElementCreatorLogic logic = new DBElementCreatorLogic()) {
            if (logic.checkNonAccessory(creatorID, elementID))
                return new StatusResponse("User has no access to the element (or this element doesn't exists)", 403);
            if (manager.updateElementInDataBase(route, elementID)) {

                collectionHandler.getCollection().removeIf(routeToRm -> Objects.equals(routeToRm.getId(), elementID));

                logger.info("Updated ID value: " + elementID);
                route.setId(elementID);

                collectionHandler.addElementToCollection(route);
                response = new StatusResponse("Element updated!", 200);
            } else {
                response = new StatusResponse("Element with that id doesn't exists.", 2);
                logger.warn(response.response());
            }
        } catch (SQLException | IOException e) {
            response = new StatusResponse("Something went wrong during updating element. Ask server administrator for further information.", -53);
            logger.error("Something went wrong during updating element! ", e);
        } finally {
            writeLock.unlock();
        }

        return response;
    }

    public <T extends Collection<Route>> T getAccessibleCollection(long callerID, Supplier<T> constructor) throws SQLException, IOException {
        readLock.lock();
        T result = constructor.get();
        try (DBCollectionLoader<T> loader = new DBCollectionLoader<>(result)) {
            loader.loadFromDB(callerID);
        }
        readLock.unlock();
        return result;
    }

    public boolean removeFromCollectionAndDB(long creatorID, long routeID) {
        writeLock.lock();
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();
        try (DBCollectionManager manager = new DBCollectionManager();
             DBElementCreatorLogic logic = new DBElementCreatorLogic()) {
            if (logic.checkNonAccessory(creatorID, routeID)) return false;
            if (manager.removeElementFromDatabase(routeID)) {
                collectionHandler.getCollection().removeIf(route -> Objects.equals(route.getId(), routeID));
            } else {
                logger.warn("Element with that id doesn't exists.");
                return false;
            }
        } catch (SQLException | IOException e) {
            logger.error("Something went wrong during updating element! ", e);
            return false;
        } finally {
            writeLock.unlock();
        }
        return true;
    }

    public StatusResponse clearCollectionInDBAndMemory(long creatorID) {
        StatusResponse response;
        writeLock.lock();
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();
        int count = 0;
        try {
            HashSet<Route> accessibleCollection = getAccessibleCollection(creatorID, HashSet::new);
            try (DBCollectionManager manager = new DBCollectionManager()) {
                for (Route route : accessibleCollection) {
                    if (manager.removeElementFromDatabase(route.getId())) {
                        collectionHandler.getCollection().removeIf(routeToRm -> Objects.equals(routeToRm.getId(), route.getId()));
                        count++;
                    }
                }
            }
            response = new StatusResponse("Removed " + count + " of " + accessibleCollection.size() + " available elements", 199);
        } catch (SQLException | IOException e) {
            response = new StatusResponse("Something went wrong during removing elements. Ask server administrator for further information.", -63);
            logger.error("Something went wrong during removing elements! ", e);
        } finally {
            writeLock.unlock();
        }
        return response;
    }
}
