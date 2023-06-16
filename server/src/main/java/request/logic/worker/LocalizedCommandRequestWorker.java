package request.logic.worker;

import command.manager.CommandManager;
import command.manager.commands.intrface.BaseCommand;
import command.manager.commands.intrface.LocalizableCommand;
import request.ArgumentCommandClientRequest;
import request.CommandClientRequest;
import request.LocalizedCommandClientRequest;
import request.logic.request.ServerRequest;

import java.util.LinkedHashMap;
import java.util.Locale;

public class LocalizedCommandRequestWorker implements RequestWorker {

    private final LinkedHashMap<Class<?>, RequestWorker> workers;

    public LocalizedCommandRequestWorker() {
        workers = new LinkedHashMap<>();
    }

    @Override
    public void workWithRequest(ServerRequest request) {
        LocalizedCommandClientRequest requestToWork = (LocalizedCommandClientRequest) request.getUserRequest();
        CommandManager manager = new CommandManager();
        for (BaseCommand command : manager.getCommands().values())
            if (command instanceof LocalizableCommand)
                ((LocalizableCommand) command).setLocale(requestToWork.getLocale());
        workers.put(CommandClientRequest.class, new CommandClientRequestWorker(manager));
        workers.put(ArgumentCommandClientRequest.class, new ArgumentCommandClientRequestWorker<>(manager));
        new RequestWorkerManager(workers).workWithRequest(new ServerRequest(requestToWork.getRequest(), request.getFrom(), request.getConnection()));
    }
}
