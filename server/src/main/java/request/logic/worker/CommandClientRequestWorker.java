package request.logic.worker;

import command.manager.CommandManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.logic.request.ServerRequest;
import request.CommandClientRequest;

public class CommandClientRequestWorker implements RequestWorker {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");

    private final CommandManager manager;

    public CommandClientRequestWorker() {
        this.manager = new CommandManager();
    }

    public CommandClientRequestWorker(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void workWithRequest(ServerRequest request) {
        CommandClientRequest requestToWork = (CommandClientRequest) request.getUserRequest();
        manager.executeCommand(requestToWork, request.getFrom(), request.getConnection());
    }
}
