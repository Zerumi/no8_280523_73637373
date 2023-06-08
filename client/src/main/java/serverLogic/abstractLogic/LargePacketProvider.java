package serverLogic.abstractLogic;

public interface LargePacketProvider extends ServerResponseProvider {
    void acceptReadingOver();
}
