package Logic;

import Model.Vertex;
import Enum.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapLogic {
    private final int totalGrids;
    private final int column;
    private Random random;
    private List<Vertex> nodes = new ArrayList<>();


    public MapLogic(int maxSize, int column) {
        this.totalGrids = maxSize;
        this.column = column;
        this.random = new Random();
        createMap();
    }

    private void createMap() {
        TileObject object;
        for (int i = 0; i < totalGrids; i++) {
            if (this.random.nextInt(6) == 2)
                object = TileObject.WALL;
            else if (this.random.nextInt(100) == 0)
                object = TileObject.POWERUP;
            else
                object = TileObject.WALKABLE;

            Vertex location = new Vertex("Node_" + i, "Node_" + i, i, object);
            nodes.add(location);
        }
    }

    public List<Vertex> getMap() {
        return this.nodes;
    }
}
