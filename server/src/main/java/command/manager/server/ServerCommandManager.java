package command.manager.server;

import command.manager.commands.BaseCommand;
import command.manager.server.commands.ShowSessions;
import exception.UnknownCommandException;

import java.util.LinkedHashMap;
import java.util.Optional;

public class ServerCommandManager {
    private final LinkedHashMap<String, BaseCommand> serverCommands;

    public ServerCommandManager() {
        serverCommands = new LinkedHashMap<>();
        serverCommands.put("sessions", new ShowSessions());
    }

    public void executeCommand(String[] args) throws UnknownCommandException {
        Optional.ofNullable(serverCommands.get(args[0])).orElseThrow(()
                -> new UnknownCommandException("Указанная команда не была обнаружена")).execute(args);
    }
}
