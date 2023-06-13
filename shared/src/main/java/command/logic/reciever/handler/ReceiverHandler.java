package command.logic.reciever.handler;

import command.logic.reciever.receiver.ExternalBaseReceiver;

import java.util.ArrayList;

public abstract class ReceiverHandler {
    public abstract void addReceiver(ExternalBaseReceiver receiver);

    public abstract ArrayList<? extends ExternalBaseReceiver> getReceivers();
}
