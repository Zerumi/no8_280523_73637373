package command.logic.reciever.receiver;

import command.logic.CommandDescription;

public interface ExternalBaseReceiver {
    boolean receiveCommand(CommandDescription command, String[] args) throws Exception;
}
