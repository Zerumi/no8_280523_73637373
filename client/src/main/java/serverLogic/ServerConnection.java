package serverLogic;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Provides abstract server connection. Use factory methods to create the connection.
 *
 * @author zerumi
 * @see ServerConnectionFactory
 * @since 2.0
 */
public interface ServerConnection {
    /**
     * Method for open a connection.
     *
     * @throws IOException if I/O occurs
     */
    void openConnection() throws IOException;

    /**
     * Method for close a connection
     *
     * @throws IOException if I/O occurs
     */
    void closeConnection() throws IOException;

    /**
     * Method for send data to a server
     *
     * @param bytesToSend bytes to send
     * @throws IOException if I/O occurs
     */
    void sendData(byte[] bytesToSend, ServerResponseProvider... providers) throws IOException;

    ByteArrayInputStream listenServer() throws IOException;
}
