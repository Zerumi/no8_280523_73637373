package command.manager.server.commands;

import client.logic.SessionHandler;
import command.manager.commands.intrface.BaseCommand;
import response.CommandStatusResponse;

public class RemoveSession implements BaseCommand {

    @Override
    public String getName() {
        return "rm_session";
    }

    @Override
    public String getDescr() {
        return "Force user to be unauthorized";
    }

    @Override
    public void execute(String[] args) throws IllegalArgumentException {
        long id = Long.parseLong(args[1]);
        SessionHandler.getInstance().getAllSessions().removeIf(x -> x.getSessionId() == id);
        System.out.println("removed");
    }

    @Override
    public CommandStatusResponse getResponse() {
        return null;
    }
}
