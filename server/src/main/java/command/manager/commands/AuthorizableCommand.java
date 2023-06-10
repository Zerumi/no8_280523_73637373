package command.manager.commands;

import command.manager.preprocessing.PreProcessingCommandInterface;

public interface AuthorizableCommand extends PreProcessingCommandInterface {
    void setCallerID(long id);
}
