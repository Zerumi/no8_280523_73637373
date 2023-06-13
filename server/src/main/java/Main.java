import command.manager.server.ServerCommandManager;
import database.logic.element.DBCollectionLoader;
import exception.UnknownCommandException;
import model.Route;
import model.handler.CollectionHandler;
import model.handler.RoutesHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.logic.RequestReader;
import request.logic.StatusRequest;
import request.logic.worker.RequestWorkerManager;
import request.logic.request.ServerRequest;
import request.BaseRequest;
import server.logic.datagram.DatagramServerConnectionFactory;
import server.logic.abstrct.ServerConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SuppressWarnings("InfiniteLoopStatement")
public class Main {
    public static final int PORT = 50456;
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");
    private static final Executor readRqMtExecutor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        CollectionHandler<HashSet<Route>, Route> handler = RoutesHandler.getInstance();

        logger.trace("This is a server!");

        // server command manager
        Thread readServerCommands = new Thread(() -> {
            logger.info("Started server command reader");
            ServerCommandManager manager = new ServerCommandManager();
            Scanner cmdScanner = new Scanner(System.in);
            while (cmdScanner.hasNext()) {
                String line = cmdScanner.nextLine();
                if (line.isEmpty()) continue;
                try {
                    manager.executeCommand(line.split(" "));
                } catch (UnknownCommandException ex) {
                    logger.warn("Unknown command!");
                }
            }
        });

        readServerCommands.start();

        // load collection
        HashSet<Route> loadedCollection = new HashSet<>();
        try (DBCollectionLoader<HashSet<Route>> loader = new DBCollectionLoader<>(loadedCollection)) {
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
                readRqMtExecutor.execute(() -> {
                    try {
                        RequestReader rqReader = new RequestReader(rq.getInputStream());
                        BaseRequest baseRequest = rqReader.readObject();
                        var request = new ServerRequest(baseRequest, rq.getCallerBack(), connection);
                        RequestWorkerManager worker = new RequestWorkerManager();
                        worker.workWithRequest(request);
                    } catch (IOException e) {
                        logger.error("Something went wrong during I/O", e);
                    } catch (ClassNotFoundException e) {
                        logger.error("Class not Found", e);
                    }
                });
            } catch (Exception e) {
                logger.fatal(e);
            }
        }
    }
}
