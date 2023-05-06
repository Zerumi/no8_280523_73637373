package databaseLogic.databaseElementLogic;

import exceptions.ElementNotAddedException;
import models.Coordinates;
import models.Location;
import models.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBCollectionManager implements Closeable {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");
    private final Connection connection;

    public DBCollectionManager() throws SQLException, IOException {
        Properties info = new Properties();
        info.load(this.getClass().getResourceAsStream("/db.cfg"));
        connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/studs", info);
    }

    public boolean updateElementInDataBase(Route route) throws SQLException {
        // TODO Add cascade delete and add. Easy and simple =)
        PreparedStatement routeUpdateStatement = connection.prepareStatement("UPDATE route " +
                "SET (name, creation_date, distance) = (?, ?, ?) " +
                "WHERE route_id = ?");
        PreparedStatement coordUpdateStatement = connection.prepareStatement("UPDATE coordinates " +
                "SET (x, y) = (?, ?) " +
                "WHERE coord_id = (SELECT )");
        return false;
    }

    public boolean addElementToDataBase(Route route) throws SQLException {
        try {
            Long coordId = addCoordinates(route.getCoordinates());
            Long fromId = addLocation(route.getFrom());
            Long toId = addLocation(route.getTo());
            PreparedStatement routeAddStatement = connection.prepareStatement("INSERT INTO route(route_id, name, coordinates_id, creation_date, location_from_id, location_to_id, distance) " +
                    "VALUES(DEFAULT, ?, ?, ?, ?, ?, ?);");
            routeAddStatement.setString(1, route.getName());
            routeAddStatement.setObject(2, coordId, Types.BIGINT);
            routeAddStatement.setObject(3, Timestamp.from(route.getCreationDate().toInstant()), Types.TIMESTAMP);
            routeAddStatement.setObject(4, fromId, Types.BIGINT);
            routeAddStatement.setObject(5, toId, Types.BIGINT);
            routeAddStatement.setObject(6, route.getDistance(), Types.INTEGER);
            routeAddStatement.execute();
            return true;
        } catch (ElementNotAddedException e) {
            logger.error("We couldn't push element to a database");
        }
        return false;
    }

    private Long addCoordinates(Coordinates coordinates) throws SQLException, ElementNotAddedException {
        if (coordinates == null) return null;
        // add coords
        PreparedStatement preparedStatementCoordinates = connection.prepareStatement("INSERT INTO coordinates(coord_id, x, y) " +
                "VALUES(DEFAULT, ?, ?)");
        preparedStatementCoordinates.setDouble(1, coordinates.getX());
        preparedStatementCoordinates.setFloat(2, coordinates.getY());
        preparedStatementCoordinates.execute();
        preparedStatementCoordinates.close();
        // get coordid
        Statement getLastIdStatement = connection.createStatement();
        ResultSet rs = getLastIdStatement.executeQuery("SELECT currval('coordinates_coord_id_seq');");
        long coordId;
        if (rs.next()) {
            coordId = rs.getLong(1);
        } else throw new ElementNotAddedException();
        logger.debug("gen coordid: " + coordId);
        return coordId;
    }

    private Long addLocation(Location location) throws SQLException, ElementNotAddedException {
        if (location == null) return null;
        // add coords
        PreparedStatement preparedStatementLocation = connection.prepareStatement("INSERT INTO location(location_id, x, y, z, name) " +
                "VALUES(DEFAULT, ?, ?, ?, ?)");
        preparedStatementLocation.setFloat(1, location.getX());
        preparedStatementLocation.setLong(2, location.getY());
        preparedStatementLocation.setLong(3, location.getZ());
        preparedStatementLocation.setString(4, location.getName());
        preparedStatementLocation.execute();
        preparedStatementLocation.close();
        // get locID
        Statement getLastIdStatement = connection.createStatement();
        ResultSet rs = getLastIdStatement.executeQuery("SELECT currval('location_location_id_seq1');");
        long locationID;
        if (rs.next()) {
            locationID = rs.getLong(1);
        } else throw new ElementNotAddedException();
        logger.debug("gen locID: " + locationID);
        return locationID;
    }

    @Override
    public void close() throws IOException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }
}
