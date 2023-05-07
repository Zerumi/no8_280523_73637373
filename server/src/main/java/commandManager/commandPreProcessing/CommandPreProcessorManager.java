package commandManager.commandPreProcessing;

import commandManager.commandPreProcessing.preProcessors.CommandAuthorizePreProcessor;
import commandManager.commandPreProcessing.preProcessors.CommandPreProcessor;
import commandManager.commands.AuthorizableCommand;
import commandManager.commands.BaseCommand;
import exceptions.PreProceedingFailedException;
import exceptions.ProcessionInterruptedException;
import requestLogic.CallerBack;
import serverLogic.ServerConnection;

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
