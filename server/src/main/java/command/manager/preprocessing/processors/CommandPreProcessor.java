package command.manager.preprocessing.processors;

import command.manager.commands.BaseCommand;
import exception.PreProceedingFailedException;
import exception.ProcessionInterruptedException;
import request.logic.CallerBack;
import server.logic.abstrct.ServerConnection;

public interface CommandPreProcessor {
    void proceed(BaseCommand command, CallerBack callerBack, ServerConnection connection) throws PreProceedingFailedException, ProcessionInterruptedException;
}
