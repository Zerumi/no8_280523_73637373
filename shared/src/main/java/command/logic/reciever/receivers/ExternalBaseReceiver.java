package command.logic.reciever.receivers;

import command.logic.CommandDescription;

public interface ExternalBaseReceiver {
    boolean receiveCommand(CommandDescription command, String[] args) throws Exception;
}
