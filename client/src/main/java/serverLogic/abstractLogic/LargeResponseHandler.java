package serverLogic.abstractLogic;

public interface LargeResponseHandler {
    void readLargeObject(int packetsCount, LargePacketProvider... packetProviders);
}
