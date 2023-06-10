package command.manager.preprocessing;

import command.manager.preprocessing.processors.CommandAuthorizePreProcessor;
import command.manager.preprocessing.processors.CommandPreProcessor;
import command.manager.commands.AuthorizableCommand;
import command.manager.commands.BaseCommand;
import exceptions.PreProceedingFailedException;
import exceptions.ProcessionInterruptedException;
import request.logic.CallerBack;
import server.logic.abstrct.ServerConnection;

import java.util.LinkedHashMap;

public class CommandPreProcessorManager {
    private final LinkedHashMap<Class<? extends PreProcessingCommandInterface>, CommandPreProcessor> preProcessorLinkedHashMap;

    public CommandPreProcessorManager() {
        preProcessorLinkedHashMap = new LinkedHashMap<>();
        preProcessorLinkedHashMap.put(AuthorizableCommand.class, new CommandAuthorizePreProcessor());
    }

    public void preProceed(BaseCommand command, CallerBack callerBack, ServerConnection connection) throws PreProceedingFailedException, ProcessionInterruptedException {
        Class<PreProcessingCommandInterface> baseClass = PreProcessingCommandInterface.class;
        Class<? extends BaseCommand> commandClass = command.getClass();
        if (baseClass.isAssignableFrom(commandClass)) {
            for (Class<? extends PreProcessingCommandInterface> infce : preProcessorLinkedHashMap.keySet()) {
                if (infce.isAssignableFrom(commandClass))
                    preProcessorLinkedHashMap.get(infce).proceed(command, callerBack, connection);
            }
        }
    }
}
