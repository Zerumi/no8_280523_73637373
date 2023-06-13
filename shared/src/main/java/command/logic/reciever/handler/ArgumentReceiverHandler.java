package command.logic.reciever.handler;

import command.logic.reciever.receiver.ExternalArgumentReceiver;
import command.logic.reciever.receiver.ExternalBaseReceiver;

import java.util.ArrayList;

public class ArgumentReceiverHandler<T> extends ReceiverHandler {

    private final ArrayList<ExternalArgumentReceiver<T>> receivers;
    Class<T> argType;

    {
        receivers = new ArrayList<>();
    }

    public ArgumentReceiverHandler(Class<T> argType) {
        this.argType = argType;
    }


    @Override
    @SuppressWarnings("unchecked")
    public void addReceiver(ExternalBaseReceiver receiver) {
        if (receiver instanceof ExternalArgumentReceiver<?>) {
            if (((ExternalArgumentReceiver<?>) receiver).getArguemnt().getClass().getName().equals(argType.getName()))
                receivers.add((ExternalArgumentReceiver<T>) receiver);
        }
    }

    @Override
    public ArrayList<ExternalArgumentReceiver<T>> getReceivers() {
        return receivers;
    }
}
