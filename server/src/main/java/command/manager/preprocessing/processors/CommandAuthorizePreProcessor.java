package command.manager.preprocessing.processors;

import client.logic.AuthorizedCallerBack;
import command.manager.commands.AuthorizableCommand;
import command.manager.commands.BaseCommand;
import request.logic.CallerBack;
import server.logic.abstrct.ServerConnection;

public class CommandAuthorizePreProcessor implements CommandPreProcessor {

    @Override
    public void proceed(BaseCommand command, CallerBack callerBack, ServerConnection connection) {
        AuthorizableCommand commandToProceed = (AuthorizableCommand) command;
        AuthorizedCallerBack authorizedCallerBack = (AuthorizedCallerBack) callerBack;
        commandToProceed.setCallerID(authorizedCallerBack.getUserData().userID());
    }
}
