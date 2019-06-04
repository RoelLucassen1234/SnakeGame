package Interface;

import Models.Vertex;

import java.util.List;

public interface IGameClient {
    void move(Iplayer player);
    Iplayer getPlayer();
    List<Vertex> getNodes();
    void receiveReady(int playerNumber);
    void receivePosition(int playernumber, int position);
}
