package command.manager.commands.intrface;

import command.manager.preprocessing.PreProcessingCommandInterface;

public interface AuthorizableCommand extends PreProcessingCommandInterface {
    void setCallerID(long id);
}
