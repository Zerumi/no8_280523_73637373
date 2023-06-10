package command.logic.external.recievers;

import command.logic.CommandDescription;
import command.logic.reciever.receivers.ExternalBaseReceiver;

import java.util.Objects;

public class ExitReceiver implements ExternalBaseReceiver {

    @Override
    public boolean receiveCommand(CommandDescription command, String[] args) {
        if (!Objects.equals(command.getName(), "exit")) return true;

        System.exit(0);
        return false;
    }
}
