package Interface;

import Model.Vertex;

import java.util.List;

public interface IGameClient {
    void move(Iplayer player);
    Iplayer getPlayer();
    List<Vertex> getNodes();
}
