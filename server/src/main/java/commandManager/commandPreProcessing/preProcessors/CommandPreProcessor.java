package commandManager.commandPreProcessing.preProcessors;

import commandManager.commands.BaseCommand;
import exceptions.PreProceedingFailedException;
import exceptions.ProcessionInterruptedException;
import request.logic.CallerBack;
import serverLogic.ServerConnection;

public interface CommandPreProcessor {
    void proceed(BaseCommand command, CallerBack callerBack, ServerConnection connection) throws PreProceedingFailedException, ProcessionInterruptedException;
}
