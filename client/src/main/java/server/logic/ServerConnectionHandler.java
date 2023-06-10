package server.logic;

import server.logic.abstrct.ServerConnection;

public class ServerConnectionHandler {

    private static ServerConnection currentConnection;

    public static void setServerConnection(ServerConnection connection) {
        currentConnection = connection;
    }

    public static ServerConnection getCurrentConnection() {
        return currentConnection;
    }
}
