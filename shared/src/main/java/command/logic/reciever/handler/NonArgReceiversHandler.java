package command.logic.reciever.handler;

import command.logic.reciever.receiver.ExternalBaseReceiver;

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
