package databaseLogic.databaseElementLogic;

import models.Route;
import models.handlers.CollectionHandler;
import models.handlers.RoutesHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responseLogic.StatusResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;

public class DBIntegrationUtility {
    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab6");

    public static StatusResponse addRouteToDBAndCollection(Route route) {
        StatusResponse response = null;

        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();
        try (DBCollectionManager manager = new DBCollectionManager()) {
            if (manager.addElementToDataBase(route)) {
                collectionHandler.addElementToCollection(route);
                response = new StatusResponse("Element added!", 200);
            }
        } catch (SQLException | IOException e) {
            response = new StatusResponse("Something went wrong during adding element. Ask server administrator for further information.", -53);
            logger.error("Something went wrong during adding element! ", e);
        }

        return response;
    }
}
