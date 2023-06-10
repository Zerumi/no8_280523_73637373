package server.logic.datagram;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.logic.abstrct.ServerConnection;
import server.logic.abstrct.ServerConnectionFactory;

import java.net.SocketException;

public class DatagramServerConnectionFactory implements ServerConnectionFactory {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");

    @Override
    public ServerConnection initializeServer(int port) {
        try {
            return new DatagramServerConnection(port);
        } catch (SocketException e) {
            logger.fatal("Cannot initialize server connection!", e);
            System.exit(-1);
        }
        return null;
    }
}
