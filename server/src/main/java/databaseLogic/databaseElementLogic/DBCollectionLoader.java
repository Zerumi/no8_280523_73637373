package databaseLogic.databaseElementLogic;

import models.Coordinates;
import models.Location;
import models.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.Collection;
import java.util.Properties;

public class DBCollectionLoader<T extends Collection<Route>> implements Closeable {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");
    private final T collectionToWrite;
    private final Connection connection;

    public DBCollectionLoader(T collectionToWrite) throws SQLException, IOException {
        this.collectionToWrite = collectionToWrite;
        Properties info = new Properties();
        info.load(this.getClass().getResourceAsStream("/db.cfg"));
        connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/studs", info);
    }

    public void loadFromDB() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT route.route_id, route.name AS name, coords.x AS coord_x, coords.y AS coord_y, route.creation_date, lto.x AS lto_x, lto.y AS lto_y, lto.z AS lto_z," +
                        " lto.name AS lto_name, lfrom.x AS lfrom_x, lfrom.y AS lfrom_y, lfrom.z AS lfrom_z, lfrom.name AS lfrom_name, route.distance, coordinates_id, location_to_id, location_from_id " +
                        "FROM Route " +
                        "LEFT JOIN Coordinates coords ON coordinates_id = coord_id " +
                        "LEFT JOIN location lto ON location_to_id = lto.location_id " +
                        "LEFT JOIN location lfrom ON location_from_id = lfrom.location_id");
        while (rs.next()) {
            Route toAdd = new Route();
            toAdd.setId(rs.getLong("route_id"));
            toAdd.setName(rs.getString("name"));
            rs.getLong("coordinates_id");
            if (!rs.wasNull()) {
                Coordinates toAddCoords = new Coordinates();
                toAddCoords.setX(rs.getDouble("coord_x"));
                toAddCoords.setY(rs.getFloat("coord_y"));
                toAdd.setCoordinates(toAddCoords);
                toAdd.setCreationDate(rs.getDate("creation_date"));
            }
            rs.getLong("location_to_id");
            if (!rs.wasNull()) {
                Location toAddTo = new Location();
                toAddTo.setX(rs.getFloat("lto_x"));
                toAddTo.setY(rs.getLong("lto_y"));
                toAddTo.setZ(rs.getLong("lto_z"));
                toAddTo.setName(rs.getString("lto_name"));
                toAdd.setTo(toAddTo);
            }
            rs.getLong("location_from_id");
            if (!rs.wasNull()) {
                Location toAddFrom = new Location();
                toAddFrom.setX(rs.getFloat("lfrom_x"));
                toAddFrom.setY(rs.getLong("lfrom_y"));
                toAddFrom.setZ(rs.getLong("lfrom_z"));
                toAddFrom.setName(rs.getString("lfrom_name"));
                toAdd.setFrom(toAddFrom);
            }
            toAdd.setDistance(rs.getInt("distance"));
            collectionToWrite.add(toAdd);
        }
        rs.close();
        statement.close();
        connection.close();
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
