package command.logic.reciever.caller;

import command.logic.CommandDescription;
import command.logic.reciever.ReceiverManager;
import command.logic.reciever.enam.ReceiverType;

public class ExternalBaseReceiverCaller extends ExternalCaller {
    @Override
    public void callReceivers(ReceiverManager manager, CommandDescription description, String[] lineArgs) throws Exception {
        var receiver = manager.getReceivers(ReceiverType.NoArgs);
        boolean commandCompleted = true;
        for (int i = 0; i < receiver.size() && commandCompleted; i++) {
            commandCompleted = receiver.get(i).receiveCommand(description, lineArgs);
        }
    }
}
