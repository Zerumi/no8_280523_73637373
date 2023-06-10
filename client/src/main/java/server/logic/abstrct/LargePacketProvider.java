package server.logic.abstrct;

public interface LargePacketProvider extends ServerResponseProvider {
    void acceptReadingOver();
}
