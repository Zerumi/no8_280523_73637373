package commandManager.commands;

import databaseLogic.databaseElementLogic.DBIntegrationUtility;
import models.Route;
import models.comparators.RouteDistanceComparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Removes elements from collection greater than given in argument.
 *
 * @author Zerumi
 * @since 1.0
 */
public class RemoveGreaterCommand implements BaseCommand, ArgumentConsumer<Route>, AuthorizableCommand {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.rmGreater");
    private CommandStatusResponse response;
    private Route obj;
    private long callerID;

    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getDescr() {
        return "Removes elements from collection greater than given in argument. Comparing is set by distance. " +
                "N/B: you may only remove elements belongs to you";
    }

    @Override
    public String getArgs() {
        return "{element}";
    }

    @Override
    public void execute(String[] args) {
        RouteDistanceComparator comparator = new RouteDistanceComparator();

        logger.debug("Distance: " + obj.getDistance());

        Iterator<Route> iterator;
        try {
            iterator = DBIntegrationUtility.getInstance().getAccessibleCollection(callerID, HashSet::new).iterator();
        } catch (SQLException | IOException e) {
            response = new CommandStatusResponse("We can't got accessible collection", -501);
            return;
        }

        int count = 0;

        while (iterator.hasNext()) {
            var current = iterator.next();
            logger.debug("Comparing: current -- " + current.getDistance() + " vs " + obj.getDistance());
            if (comparator.compare(current, obj) > 0) {
                logger.debug(" -- Greater / Removing...");
                if (DBIntegrationUtility.getInstance().removeFromCollectionAndDB(callerID, current.getId())) {
                    count++;
                } else logger.warn("Element isn't removed...");
            } else {
                logger.debug(" -- Lower.");
            }
        }
        response = CommandStatusResponse.ofString("Removed " + count + " elements");
        logger.info(response.getResponse());
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }

    @Override
    public void setObj(Route obj) {
        this.obj = obj;
        obj.setCreationDate(Date.from(Instant.now()));
    }

    @Override
    public void setCallerID(long id) {
        this.callerID = id;
    }
}
