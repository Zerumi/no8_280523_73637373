package requestLogic.requestSenders;

import commandLogic.CommandDescription;
import exceptions.GotAnErrorResponseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requests.CommandClientRequest;
import responses.CommandStatusResponse;
import serverLogic.ServerConnection;

import java.io.IOException;
import java.net.PortUnreachableException;

public class CommandRequestSender {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");

    public CommandStatusResponse sendCommand(CommandDescription command, String[] args, ServerConnection connection) {
        CommandStatusResponse response = null;
        try {
            var rq = new CommandClientRequest(command, args);
            logger.info("Sending command request...");
            response = (CommandStatusResponse) new RequestSender().sendRequest(rq, connection);
        } catch (PortUnreachableException e) {
            logger.warn("Server is unavailable. Please, wait until server will come back.");
        } catch (IOException e) {
            logger.error("Something went wrong during I/O operations", e);
        } catch (GotAnErrorResponseException e) {
            logger.error("Received error from a server! " + e.getErrorResponse().getMsg());
        }
        return response;
    }
}