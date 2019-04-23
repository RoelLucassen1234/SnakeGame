package Logic;

import Model.Vertex;
import Enum.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MapLogic {
    public int getTotalGrids() {
        return totalGrids;
    }

    public int getColumn() {
        return column;
    }

    private final int totalGrids;
    private final int column;
    private Random random;
    private List<Vertex> nodes = new ArrayList<>();
    public List<Vertex> getNodes() {
        return nodes;
    }



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
            else if (this.random.nextInt(100) == 2)
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

    public Vertex getSpecificNode(int number){
      List<Vertex> node = nodes.stream().filter(vertex -> vertex.getIdNumber() == number).collect(Collectors.toList());
      return node.get(0);
    }
}
