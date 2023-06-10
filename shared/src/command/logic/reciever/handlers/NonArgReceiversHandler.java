package command.logic.reciever.handlers;

import command.logic.reciever.receivers.ExternalBaseReceiver;

import java.util.ArrayList;

public class NonArgReceiversHandler extends ReceiverHandler {

    private final ArrayList<ExternalBaseReceiver> receivers;

    {
        receivers = new ArrayList<>();
    }

    @Override
    public void addReceiver(ExternalBaseReceiver receiver) {
        receivers.add(receiver);
    }

    @Override
    public ArrayList<ExternalBaseReceiver> getReceivers() {
        return receivers;
    }
}
