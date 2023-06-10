package request.logic.workers;

import command.manager.CommandManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.logic.requests.ServerRequest;
import requests.CommandClientRequest;

public class CommandClientRequestWorker implements RequestWorker {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");

    @Override
    public void workWithRequest(ServerRequest request) {
        CommandClientRequest requestToWork = (CommandClientRequest) request.getUserRequest();
        CommandManager manager = new CommandManager();
        manager.executeCommand(requestToWork, request.getFrom(), request.getConnection());
    }
}
