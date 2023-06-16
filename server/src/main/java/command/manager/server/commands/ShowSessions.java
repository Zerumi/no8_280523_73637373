package command.manager.server.commands;

import client.logic.SessionHandler;
import command.manager.commands.intrface.BaseCommand;
import response.CommandStatusResponse;

public class ShowSessions implements BaseCommand {

    @Override
    public String getName() {
        return "sessions";
    }

    @Override
    public String getDescr() {
        return "Shows current sessions on server.";
    }

    @Override
    public void execute(String[] args) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        SessionHandler.getInstance().getAllSessions().forEach(session ->
                sb.append(session).append('\n'));
        System.out.println(sb);
        if (sb.isEmpty()) System.out.println("No sessions");
    }

    @Override
    public CommandStatusResponse getResponse() {
        return null;
    }
}
