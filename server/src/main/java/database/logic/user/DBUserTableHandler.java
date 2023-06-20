package database.logic.user;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

public class DBUserTableHandler implements Closeable {
    private final Connection connection;

    public DBUserTableHandler() throws IOException, SQLException {
        Properties info = new Properties();
        info.load(this.getClass().getResourceAsStream("/db.cfg"));
        connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/studs", info);
    }

    public HashMap<Long, Long> getOwnership() throws SQLException {
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM route_creator");
        HashMap<Long, Long> result = new HashMap<>();
        while (rs.next()) {
            result.put(rs.getLong(1), rs.getLong(2));
        }
        rs.close();
        st.close();
        return result;
    }

    public long getOwnership(long id) throws SQLException {
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM route_creator WHERE route_id = " + id);
        long result = -1;
        if (rs.next()) {
            result = rs.getLong(2);
        }
        rs.close();
        st.close();
        return result;
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
