package command.logic.reciever.handlers;

import command.logic.reciever.receivers.ExternalBaseReceiver;

import java.util.ArrayList;

public abstract class ReceiverHandler {
    public abstract void addReceiver(ExternalBaseReceiver receiver);

    public abstract ArrayList<? extends ExternalBaseReceiver> getReceivers();
}
