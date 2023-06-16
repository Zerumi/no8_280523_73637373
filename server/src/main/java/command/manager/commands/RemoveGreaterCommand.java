package command.manager.commands;

import command.manager.commands.intrface.ArgumentConsumer;
import command.manager.commands.intrface.AuthorizableCommand;
import command.manager.commands.intrface.BaseCommand;
import command.manager.commands.intrface.LocalizableCommand;
import database.logic.element.DBIntegrationUtility;
import model.Route;
import model.comparator.RouteDistanceComparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import response.CommandStatusResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.time.Instant;
import java.util.*;

/**
 * Removes elements from collection greater than given in argument.
 *
 * @author Zerumi
 * @since 1.0
 */
public class RemoveGreaterCommand implements BaseCommand, ArgumentConsumer<Route>, AuthorizableCommand, LocalizableCommand {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6.commands.rmGreater");
    private CommandStatusResponse response;
    private Route obj;
    private long callerID;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("l10n.command.CommandResourceBundle", Locale.US);
    private ResourceBundle commandBundle = ResourceBundle.getBundle("l10n.command.removeG.RemoveGreaterCommandBundle", Locale.US);

    @Override
    public String getName() {
        return resourceBundle.getString("remove_greater");
    }

    @Override
    public String getDescr() {
        return resourceBundle.getString("d_remove_greater");
    }

    @Override
    public String getArgs() {
        return resourceBundle.getString("a_remove_greater");
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
        MessageFormat mf = new MessageFormat(commandBundle.getString("executed"));
        String res = mf.format(new Object[]{count});
        response = CommandStatusResponse.ofString(res);
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

    @Override
    public void setLocale(Locale locale) {
        ResourceBundle.clearCache();
        Locale.setDefault(locale);
        resourceBundle = ResourceBundle.getBundle("l10n.command.CommandResourceBundle", locale);
        commandBundle = ResourceBundle.getBundle("l10n.command.removeG.RemoveGreaterCommandBundle", locale);
    }
}
