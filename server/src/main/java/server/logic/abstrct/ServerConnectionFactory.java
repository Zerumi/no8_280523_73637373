package server.logic.abstrct;

public interface ServerConnectionFactory {
    ServerConnection initializeServer(int port);
}
