import gui.frames.AuthWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import serverLogic.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

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

        // most of the logic are deprecated now (staying here because I need code-snippets from here).

        // server connecting
        /* try {
            // request commands
            boolean commandsNotLoaded = true;
            int waitingCount = 4000;
            while (commandsNotLoaded) {
                try {
                    CommandLoaderUtility.initializeCommands();
                    CommandExecutor executor = new CommandExecutor(CommandDescriptionHolder.getInstance().getCommands(),
                            System.in, CommandMode.CLI_UserMode);
                    commandsNotLoaded = false;

                    // start executing
                    System.out.println("Welcome to CLI! We've established connection to a server.");
                    System.out.println("Now you can enter the commands. Use help for reference.");
                    executor.startExecuting();
                } catch (CommandsNotLoadedException e) {
                    logger.warn("We couldn't get commands from server last time, so now we'll try to do this again...");

                    AtomicInteger secondsRemained = new AtomicInteger(waitingCount / 1000 - 1);

                    javax.swing.Timer timer = new Timer(1000, (x) -> logger.info("Re-attempt in " +
                            secondsRemained.getAndDecrement() + " seconds."));

                    timer.start();

                    CountDownLatch latch = new CountDownLatch(1);

                    int finalWaitingCount = waitingCount;
                    Runnable wait = () -> {
                        try {
                            Thread.sleep(finalWaitingCount);
                            latch.countDown();
                        } catch (InterruptedException ex) {
                            logger.info("Continuing...");
                        }
                    };

                    Thread tWait = new Thread(wait);

                    tWait.start();

                    try {
                        latch.await();
                        timer.stop();
                        tWait.interrupt();
                    } catch (InterruptedException ex) {
                        logger.info("Interrupted");
                    }

                    waitingCount += 2000;
                }
            }
        } catch (UnknownHostException ex) {
            logger.fatal("Can't find host.");
        } catch (IOException ex) {
            logger.error("Something went wrong during IO operations.");
        } */
    }
}