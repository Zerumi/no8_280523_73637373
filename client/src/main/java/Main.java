import gui.frame.AuthWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.logic.ServerConnectionHandler;
import server.logic.abstrct.ServerConnection;
import server.logic.udp.UdpConnectionBlockDecorator;
import server.logic.udp.UdpServerConnection;
import server.logic.udp.UdpServerConnectionFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.prefs.Preferences;

/**
 * Program entry point class. Contains main() method.
 * This program is managing collection of type <code>HashSet&#8249;Route></code>
 *
 * @author zerumi
 * @since 1.0
 */
public class Main {
    public static final int PORT = 50456;
    private static final Logger logger = LogManager.getLogger("lab6");

    /**
     * Program entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        try {
            Preferences root = Preferences.userRoot();
            Preferences node = root.node("/com/zerumi/github/lab8");
            String defaultLocale = node.get("default_locale", null);
            if (defaultLocale != null && !defaultLocale.isBlank())
                Locale.setDefault(Locale.forLanguageTag(defaultLocale));

            ServerConnection connection = new UdpConnectionBlockDecorator(
                    (UdpServerConnection) new UdpServerConnectionFactory().openConnection(
                            InetAddress.getLocalHost(), PORT), true);
            ServerConnectionHandler.setServerConnection(connection);
            connection.openConnection();

            EventQueue.invokeLater(() -> {
                AuthWindow window = new AuthWindow();
                window.setTitle("Authentication");
                window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                window.setVisible(true);
            });

        } catch (UnknownHostException e) {
            logger.error("Can't find host", e);
        } catch (IOException e) {
            logger.error("Something went wrong during I/O operations", e);
        }
    }
}