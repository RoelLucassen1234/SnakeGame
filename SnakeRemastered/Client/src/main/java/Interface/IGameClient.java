package Interface;

public interface IGameClient {
    void move(Iplayer player);
    Iplayer getPlayer();
    void receiveReady(int playerNumber);
    void receivePosition(int playernumber, int position);
}
