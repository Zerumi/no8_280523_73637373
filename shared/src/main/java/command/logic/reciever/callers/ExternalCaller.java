package command.logic.reciever.callers;

import command.logic.CommandDescription;
import command.logic.reciever.ReceiverManager;

import java.io.Serializable;

public abstract class ExternalCaller implements Serializable {
    public abstract void callReceivers(ReceiverManager manager, CommandDescription description, String[] lineArgs) throws Exception;
}
