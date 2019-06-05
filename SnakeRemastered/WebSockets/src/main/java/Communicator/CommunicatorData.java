package Communicator;

public class CommunicatorData {
    public static long getSeed() {
        return seed;
    }

    public static void setSeed(long seed) {
        CommunicatorData.seed = seed;
    }

    static long seed;
}
