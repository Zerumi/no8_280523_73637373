package databaseUsersLogic;

import authorization.AuthorizedUserData;
import exceptions.AuthorizationException;
import exceptions.RegistrationFailedException;
import org.apache.commons.lang3.ArrayUtils;
import requestLogic.CallerBack;
import requests.authCredentials.AuthenticationData;
import requests.authCredentials.RegistrationData;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Properties;

public class DBUserManager implements Closeable {

    private final PasswordEncryption encryptionAlg;
    private final Connection connection;

    public DBUserManager(PasswordEncryption encryptionAlg) throws IOException, SQLException {
        this.encryptionAlg = encryptionAlg;
        Properties info = new Properties();
        info.load(this.getClass().getResourceAsStream("/db.cfg"));
        connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/studs", info);
    }

    public AuthorizedUserData addUserToDatabase(CallerBack callerBack, RegistrationData data) throws SQLException, RegistrationFailedException {
        String login = data.getLogin();
        String name = data.getName();
        char[] passSalt = encryptionAlg.generateSalt();
        String passHash = encryptionAlg.encrypt(ArrayUtils.addAll(PasswordEncryption.getPepper(),
                ArrayUtils.addAll(data.getPassword(), passSalt))); // combining 3 arrays okda...
        String regIP = callerBack.getAddress().toString();
        Timestamp regTime = Timestamp.from(Instant.now());
        Timestamp lastLogin = Timestamp.from(Instant.EPOCH);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO \"User\"" +
                "(user_id, login, name, pass_hash, pass_salt, reg_ip, reg_time, last_login) " +
                "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?);");
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, passHash);
        preparedStatement.setString(4, new String(passSalt));
        preparedStatement.setString(5, regIP);
        preparedStatement.setTimestamp(6, regTime);
        preparedStatement.setTimestamp(7, lastLogin);

        preparedStatement.execute();

        long userID;
        Statement getUserID = connection.createStatement();
        ResultSet rs = getUserID.executeQuery("SELECT currval('User_user_id_seq');");
        if (rs.next()) {
            userID = rs.getLong(1);
        } else throw new RegistrationFailedException();
        return new AuthorizedUserData(userID, name, login, LocalDate.ofInstant(lastLogin.toInstant(), ZoneOffset.UTC), regIP, LocalDate.ofInstant(regTime.toInstant(), ZoneOffset.UTC));
    }

    public AuthorizedUserData getUserFromDatabase(AuthenticationData regData) throws AuthorizationException {
        return null; // todo: authorize in db
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
