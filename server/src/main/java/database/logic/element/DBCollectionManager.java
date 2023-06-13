package database.logic.element;

import exception.ElementNotAddedException;
import model.Coordinates;
import model.Location;
import model.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBCollectionManager implements Closeable {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");
    private final Connection connection;

    protected DBCollectionManager() throws SQLException, IOException {
        Properties info = new Properties();
        info.load(this.getClass().getResourceAsStream("/db.cfg"));
        connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/studs", info);
    }

    protected boolean updateElementInDataBase(Route route, Long id) throws SQLException {
        try {
            Coordinates coordinates = route.getCoordinates();
            PreparedStatement updateCoordStatement = connection.prepareStatement("UPDATE Coordinates AS c " +
                    "SET x = ?, " +
                    "    y = ? " +
                    "FROM Route AS r " +
                    "WHERE r.route_id = ? AND r.coordinates_id = c.coord_id;");
            updateCoordStatement.setDouble(1, coordinates.getX());
            updateCoordStatement.setFloat(2, coordinates.getY());
            updateCoordStatement.setLong(3, id);
            updateCoordStatement.close();
            Location to = route.getTo();
            Statement getToId = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet lastToIdRs = getToId.executeQuery(
                    "SELECT * FROM route " +
                            "WHERE route_id = " + id);
            updateNullableLocation(to, lastToIdRs, 5);
            lastToIdRs.close();
            getToId.close();
            Location from = route.getFrom();
            Statement getFromId = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet lastFromIdRs = getFromId.executeQuery(
                    "SELECT * FROM route " +
                            "WHERE route_id = " + id);
            updateNullableLocation(from, lastFromIdRs, 6);
            lastFromIdRs.close();
            getFromId.close();
            PreparedStatement updateRouteStatement = connection.prepareStatement(
                    "UPDATE Route " +
                            "SET name = ?, " +
                            "    creation_date = ?, " +
                            "    distance = ? " +
                            "WHERE route_id = ?;");
            updateRouteStatement.setString(1, route.getName());
            updateRouteStatement.setTimestamp(2, Timestamp.from(route.getCreationDate().toInstant()));
            updateRouteStatement.setInt(3, route.getDistance());
            updateRouteStatement.setLong(4, id);
            updateRouteStatement.execute();
            updateRouteStatement.close();
            return true;
        } catch (ElementNotAddedException e) {
            logger.error("Something went wrong during fetching ID: ", e);
        }
        return false;
    }

    protected void updateNullableLocation(Location location, ResultSet lastLocationIdRs, int columnIndex) throws ElementNotAddedException, SQLException {
        Long toId = getIdFromStatement(lastLocationIdRs, columnIndex);
        if (toId == null) {
            Long newId = addLocation(location);
            if (newId != null) {
                lastLocationIdRs.updateLong(columnIndex, newId);
                lastLocationIdRs.updateRow();
            }
        } else updateNonNullLocation(location, toId);
    }

    private void updateNonNullLocation(Location location, long locationId) throws SQLException {
        PreparedStatement updateLocationStatement = connection.prepareStatement(
                "UPDATE Location AS l " +
                        "SET x = ?," +
                        "    y = ?," +
                        "    z = ?," +
                        "    name = ? " +
                        "WHERE l.location_id = ?;");
        updateLocationStatement.setFloat(1, location.getX());
        updateLocationStatement.setLong(2, location.getY());
        updateLocationStatement.setLong(3, location.getZ());
        updateLocationStatement.setString(4, location.getName());
        updateLocationStatement.setLong(5, locationId);
        updateLocationStatement.executeUpdate();
        updateLocationStatement.close();
    }

    protected boolean addElementToDataBase(Route route) throws SQLException {
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
            routeAddStatement.close();

            // get id
            Statement getLastIdStatement = connection.createStatement();
            ResultSet rs = getLastIdStatement.executeQuery("SELECT currval('route_route_id_seq');");
            route.setId(getIdFromStatement(rs, 1));

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
        var coordId = getIdFromStatement(rs, 1);
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
        var locationID = getIdFromStatement(rs, 1);
        logger.debug("gen locID: " + locationID);
        return locationID;
    }

    private Long getIdFromStatement(ResultSet statementResult, int columnIndex) throws SQLException, ElementNotAddedException {
        long result;
        if (statementResult.next()) {
            result = statementResult.getLong(columnIndex);
            if (statementResult.wasNull()) {
                return null;
            }
        } else {
            throw new ElementNotAddedException();
        }
        return result;
    }

    protected boolean removeElementFromDatabase(Long id) throws SQLException {
        Statement getRouteToRemove = connection.createStatement();
        ResultSet rs = getRouteToRemove.executeQuery("SELECT * FROM route " +
                "WHERE route_id = " + id);
        if (rs.next()) {
            long coordId = rs.getLong("coordinates_id");
            long toId = rs.getLong("location_to_id");
            long fromId = rs.getLong("location_from_id");

            Statement removeRouteStatement = connection.createStatement();
            removeRouteStatement.executeUpdate("DELETE FROM route " +
                    "WHERE route_id = " + id);
            removeRouteStatement.close();

            Statement removeCoordStatement = connection.createStatement();
            removeCoordStatement.executeUpdate("DELETE FROM coordinates " +
                    "WHERE coord_id = " + coordId);
            removeCoordStatement.close();

            Statement removeLocationsStatement = connection.createStatement();
            removeLocationsStatement.executeUpdate("DELETE FROM location " +
                    "WHERE location_id = " + toId + " OR location_id = " + fromId);
            removeLocationsStatement.close();
            return true;
        } else return false;
    }

    public void updateSingleObject(Long objId, int dbIndex, Object valueToSet) throws SQLException {
        Statement updateStat = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        logger.info("updating objID " + objId);
        logger.info("updating columnID " + dbIndex);
        logger.info("updating obj " + valueToSet);
        ResultSet rs = updateStat.executeQuery("SELECT route.route_id, route.name, c.x, c.y, creation_date, l.x, l.y, l.z, l.name, l2.x, l2.y, l2.z, l2.name, distance, coordinates_id, coord_id, location_from_id, location_to_id, l.location_id, l2.location_id FROM route\n" +
                "RIGHT JOIN coordinates c on c.coord_id = route.coordinates_id\n" +
                "RIGHT JOIN location l on l.location_id = route.location_from_id\n" +
                "RIGHT JOIN location l2 on l2.location_id = route.location_to_id\n" +
                "WHERE route.route_id = " + objId);
        if (rs.next()) {
            rs.updateObject(++dbIndex, valueToSet);
            rs.updateRow();
        }
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
