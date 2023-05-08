package databaseLogic.databaseElementLogic;

import multiThreadLogic.CollectinonSyncronize;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.locks.Lock;

public class DBElementCreatorLogic implements Closeable {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");
    private final Connection connection;
    private final Lock readLock;
    private final Lock writeLock;

    protected DBElementCreatorLogic() throws SQLException, IOException {
        Properties info = new Properties();
        info.load(this.getClass().getResourceAsStream("/db.cfg"));
        connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/studs", info);
        readLock = CollectinonSyncronize.getInstance().getLock().readLock();
        writeLock = CollectinonSyncronize.getInstance().getLock().writeLock();
    }

    protected void addCreatorToDB(long routeID, long creatorID) throws SQLException {
        writeLock.lock();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO route_creator(" +
                "route_id, user_id) VALUES (?, ?)");
        statement.setLong(1, routeID);
        statement.setLong(2, creatorID);
        statement.executeUpdate();
        statement.close();
        writeLock.unlock();
    }

    protected boolean checkNonAccessory(long creatorID, long routeID) throws SQLException {
        readLock.lock();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM route_creator " +
                        "WHERE route_id = ?");
        statement.setLong(1, routeID);
        ResultSet rs = statement.executeQuery();
        readLock.unlock();
        return !rs.next() || rs.getLong(2) != creatorID;
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
