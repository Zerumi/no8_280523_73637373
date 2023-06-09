package request.logic.worker;

import command.manager.CommandManager;
import command.manager.commands.intrface.ArgumentConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request.logic.request.ServerRequest;
import request.ArgumentCommandClientRequest;

public class ArgumentCommandClientRequestWorker<T, Y> implements RequestWorker {
    private static final Logger logger = LogManager.getLogger("io.github.zerumi.lab6");

    private final CommandManager manager;

    public ArgumentCommandClientRequestWorker() {
        this.manager = new CommandManager();
    }

    public ArgumentCommandClientRequestWorker(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void workWithRequest(ServerRequest request) {
        ArgumentCommandClientRequest<T> requestToWork = (ArgumentCommandClientRequest<T>) request.getUserRequest();
        ArgumentConsumer<T> argCommand = (ArgumentConsumer<T>) manager.fromDescription(requestToWork.getCommandDescription());
        argCommand.setObj(requestToWork.getArgument());
        manager.executeCommand(requestToWork, request.getFrom(), request.getConnection());
    }
}
