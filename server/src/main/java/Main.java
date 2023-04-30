import databaseLogic.DBCollectionManager;
import exceptions.NotAvailableException;
import models.Route;
import models.handlers.CollectionHandler;
import models.handlers.RoutesHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.RequestReader;
import requestLogic.StatusRequest;
import requestLogic.requestWorkers.RequestWorkerManager;
import requestLogic.requests.ServerRequest;
import requests.BaseRequest;
import responseLogic.responseSenders.ResponseSender;
import responses.ErrorResponse;
import serverLogic.DatagramServerConnectionFactory;
import serverLogic.ServerConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;

@SuppressWarnings("InfiniteLoopStatement")
public class Main {
    public static final int PORT = 50456;
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");

    /**
     * Environment key to XML file for store collection.
     */
    public static final String ENV_KEY = "lab5";

    public static void main(String[] args) {
        CollectionHandler<HashSet<Route>, Route> handler = RoutesHandler.getInstance();

        logger.trace("This is a server!");

        // load collection
        try {
            HashSet<Route> loadedCollection = new HashSet<>();
            DBCollectionManager<HashSet<Route>> loader = new DBCollectionManager<>(loadedCollection);
            loader.loadFromDB();
            handler.setCollection(loadedCollection);
        } catch (SQLException | IOException e) {
            logger.error("Something went wrong during collection load: ", e);
        }
        System.out.println("Loaded " + handler.getCollection().size() + " elements total.");
        System.out.println();

        // commands
        logger.info("Welcome to CLI server! Now you are operating with collection of type " + handler.getCollection().getClass().getName() + ", filled with elements of type " + handler.getFirstOrNew().getClass().getName());
        logger.info("Now server is listening a requests.");

        // connection
        ServerConnection connection = new DatagramServerConnectionFactory().initializeServer(PORT);
        while (true) {
            try {
                StatusRequest rq = connection.listenAndGetData();
                if (rq.getCode() < 0) {
                    logger.debug("Status code: " + rq.getCode());
                    continue;
                }

                RequestReader rqReader = new RequestReader(rq.getInputStream());
                BaseRequest baseRequest = rqReader.readObject();
                var request = new ServerRequest(baseRequest, rq.getCallerBack(), connection);
                RequestWorkerManager worker = new RequestWorkerManager();
                worker.workWithRequest(request);
            } catch (IOException e) {
                logger.error("Something went wrong during I/O", e);
            } catch (ClassNotFoundException e) {
                logger.error("Class not Found", e);
            } catch (RuntimeException e) {
                logger.fatal(e);
            } catch (NotAvailableException e) {
                try {
                    ErrorResponse response = new ErrorResponse("Server is busy right now...");
                    ResponseSender.sendResponse(response, connection, e.getDeniedClient());
                } catch (IOException ex) {
                    logger.fatal("Can't send response", e);
                }
            }
        }
    }
}