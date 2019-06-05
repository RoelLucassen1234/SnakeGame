package Interface;

import java.io.IOException;

public interface IGameClient {
    void move(Iplayer player) throws IOException;
    Iplayer getPlayer();
    void receiveReady(int playerNumber);
    void receivePosition(int playernumber, int position);
    void getMultiplayerSeed();
    void receiveSeedCheck(long seed);

}
