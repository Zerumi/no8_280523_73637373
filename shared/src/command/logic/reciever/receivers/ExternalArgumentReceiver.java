package command.logic.reciever.receivers;

public interface ExternalArgumentReceiver<T> extends ExternalBaseReceiver {
    T getArguemnt();
}
