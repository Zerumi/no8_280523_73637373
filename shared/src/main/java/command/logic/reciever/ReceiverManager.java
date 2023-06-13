package command.logic.reciever;

import command.logic.reciever.enam.ReceiverType;
import command.logic.reciever.handler.ReceiverHandler;
import command.logic.reciever.receiver.ExternalBaseReceiver;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ReceiverManager {
    private final LinkedHashMap<ReceiverType, ReceiverHandler> receivers;

    {
        receivers = new LinkedHashMap<>();
    }

    public void registerHandler(ReceiverType type, ReceiverHandler handler) {
        receivers.put(type, handler);
    }

    public void registerReceiver(ReceiverType type, ExternalBaseReceiver receiver) {
        receivers.get(type).addReceiver(receiver);
    }

    public ArrayList<? extends ExternalBaseReceiver> getReceivers(ReceiverType type) {
        return receivers.get(type).getReceivers();
    }
}
