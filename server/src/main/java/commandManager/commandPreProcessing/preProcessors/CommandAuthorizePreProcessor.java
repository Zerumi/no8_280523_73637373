package commandManager.commandPreProcessing.preProcessors;

import clientLogic.AuthorizedCallerBack;
import commandManager.commands.AuthorizableCommand;
import commandManager.commands.BaseCommand;
import request.logic.CallerBack;
import serverLogic.ServerConnection;

public class CommandAuthorizePreProcessor implements CommandPreProcessor {

    @Override
    public void proceed(BaseCommand command, CallerBack callerBack, ServerConnection connection) {
        AuthorizableCommand commandToProceed = (AuthorizableCommand) command;
        AuthorizedCallerBack authorizedCallerBack = (AuthorizedCallerBack) callerBack;
        commandToProceed.setCallerID(authorizedCallerBack.getUserData().userID());
    }
}
