package server.logic.abstrct;

public interface LargeResponseHandler {
    void readLargeObject(int packetsCount, LargePacketProvider... packetProviders);
}
