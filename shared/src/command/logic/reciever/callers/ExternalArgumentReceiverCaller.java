package command.logic.reciever.callers;

import command.logic.reciever.ReceiverManager;
import command.logic.CommandDescription;
import command.logic.reciever.enums.ReceiverType;
import command.logic.reciever.receivers.ExternalArgumentReceiver;

import java.util.ArrayList;

public class ExternalArgumentReceiverCaller<T> extends ExternalCaller {
    @Override
    public void callReceivers(ReceiverManager manager, CommandDescription description, String[] lineArgs) throws Exception {
        for (ExternalArgumentReceiver<T> receiver : (ArrayList<ExternalArgumentReceiver<T>>) manager.getReceivers(ReceiverType.ArgumentRoute)) {
            receiver.receiveCommand(description, lineArgs);
        }
    }
}
