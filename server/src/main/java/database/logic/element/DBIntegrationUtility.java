package database.logic.element;

import exception.NotEditableException;
import javassist.NotFoundException;
import model.Route;
import model.RouteFields;
import model.collection.actions.UpdateCollectionAction;
import model.handler.CollectionHandler;
import model.handler.RoutesHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.logic.worker.ListenCollectionChangeHubWorker;
import response.CollectionUpdatedResponse;
import response.logic.StatusResponse;
import util.RouteFieldSetters;
import util.RouteFieldToRoute;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
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
        ReadWriteLock rwl = new ReentrantReadWriteLock();
        writeLock = rwl.writeLock();
        readLock = rwl.readLock();
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
        StatusResponse response = new StatusResponse("Operation interrupted.", -243);

        writeLock.lock();
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();
        Route routeToEdit = collectionHandler.getCollection().stream().filter(x -> x.getId().equals(elementID)).findFirst().orElse(null);
        if (routeToEdit != null) {
            route.setCreationDate(routeToEdit.getCreationDate());
            try (DBCollectionManager manager = new DBCollectionManager();
                 DBElementCreatorLogic logic = new DBElementCreatorLogic()) {
                if (logic.checkNonAccessory(creatorID, elementID))
                    return new StatusResponse("User has no access to the element (or this element doesn't exists)", 403);
                if (manager.updateElementInDataBase(route, elementID)) {
                    // я тоже себя ненавижу, малыш, я тоже...
                    routeToEdit.setName(route.getName());
                    ListenCollectionChangeHubWorker.sendToAllCallers(new CollectionUpdatedResponse(new UpdateCollectionAction(
                            elementID, RouteFields.NAME, route.getName()
                    )));
                    routeToEdit.setCoordinates(route.getCoordinates());
                    ListenCollectionChangeHubWorker.sendToAllCallers(new CollectionUpdatedResponse(new UpdateCollectionAction(
                            elementID, RouteFields.COORDINATES_X, route.getCoordinates().getX()
                    )));
                    ListenCollectionChangeHubWorker.sendToAllCallers(new CollectionUpdatedResponse(new UpdateCollectionAction(
                            elementID, RouteFields.COORDINATES_Y, route.getCoordinates().getY()
                    )));
                    routeToEdit.setTo(route.getTo());
                    ListenCollectionChangeHubWorker.sendToAllCallers(new CollectionUpdatedResponse(new UpdateCollectionAction(
                            elementID, RouteFields.TO_X, route.getTo().getX()
                    )));
                    ListenCollectionChangeHubWorker.sendToAllCallers(new CollectionUpdatedResponse(new UpdateCollectionAction(
                            elementID, RouteFields.TO_Y, route.getTo().getY()
                    )));
                    ListenCollectionChangeHubWorker.sendToAllCallers(new CollectionUpdatedResponse(new UpdateCollectionAction(
                            elementID, RouteFields.TO_Z, route.getTo().getZ()
                    )));
                    ListenCollectionChangeHubWorker.sendToAllCallers(new CollectionUpdatedResponse(new UpdateCollectionAction(
                            elementID, RouteFields.TO_NAME, route.getTo().getName()
                    )));
                    routeToEdit.setFrom(route.getFrom());
                    ListenCollectionChangeHubWorker.sendToAllCallers(new CollectionUpdatedResponse(new UpdateCollectionAction(
                            elementID, RouteFields.FROM_X, route.getFrom().getX()
                    )));
                    ListenCollectionChangeHubWorker.sendToAllCallers(new CollectionUpdatedResponse(new UpdateCollectionAction(
                            elementID, RouteFields.FROM_Y, route.getFrom().getY()
                    )));
                    ListenCollectionChangeHubWorker.sendToAllCallers(new CollectionUpdatedResponse(new UpdateCollectionAction(
                            elementID, RouteFields.FROM_Z, route.getFrom().getZ()
                    )));
                    ListenCollectionChangeHubWorker.sendToAllCallers(new CollectionUpdatedResponse(new UpdateCollectionAction(
                            elementID, RouteFields.FROM_NAME, route.getFrom().getName()
                    )));
                    routeToEdit.setDistance(route.getDistance());
                    ListenCollectionChangeHubWorker.sendToAllCallers(new CollectionUpdatedResponse(new UpdateCollectionAction(
                            elementID, RouteFields.DISTANCE, route.getDistance()
                    )));

                    logger.info("Updated ID value: " + elementID);
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
            response = new StatusResponse("removed elements", 199, count, accessibleCollection.size());
        } catch (SQLException | IOException e) {
            response = new StatusResponse("Something went wrong during removing elements. Ask server administrator for further information.", -63);
            logger.error("Something went wrong during removing elements! ", e);
        } finally {
            writeLock.unlock();
        }
        return response;
    }

    public StatusResponse updateSingleField(long creatorID, Long objId, RouteFields field, Object valueToSet) {
        writeLock.lock();
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();
        try (DBCollectionManager manager = new DBCollectionManager();
             DBElementCreatorLogic logic = new DBElementCreatorLogic()) {
            logger.info("checking creatorID " + creatorID + " element id " + objId);
            if (logic.checkNonAccessory(creatorID, objId)) {
                logger.warn("user has no access :(");
                return new StatusResponse("User has no access to the element", 403);
            }

            Route route = RoutesHandler.getInstance().getCollection().stream().filter(x -> x.getId().equals(objId)).findAny().orElse(null);
            RouteFieldSetters.setValue(route, field, valueToSet);
            if (route != null)
                manager.updateElementInDataBase(route, objId);

            RouteFieldToRoute.setField(
                    collectionHandler.getCollection().stream().filter(x -> x.getId().equals(objId))
                            .findAny().orElseThrow(() -> new NotFoundException("Object not found in cache!")),
                    field, valueToSet);
        } catch (SQLException | IOException | NotFoundException e) {
            logger.error("Something went wrong during updating elements! ", e);
            return new StatusResponse
                    ("Something went wrong during removing elements. " +
                            "Ask server administrator for further information.", 403);
        } catch (NotEditableException e) {
            logger.error("not editable field");
            return new StatusResponse(
                    "Field is auto-generated", 400
            );
        } finally {
            writeLock.unlock();
            logger.info("finally block");
        }
        ListenCollectionChangeHubWorker.sendToAllCallers(new CollectionUpdatedResponse(new UpdateCollectionAction(
                objId, field, valueToSet
        )));
        return new StatusResponse("Executed", 200);
    }
}
