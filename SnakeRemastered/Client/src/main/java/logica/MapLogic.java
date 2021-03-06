package logica;

import enums.TileObject;
import models.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MapLogic {


    private final int totalGrids;
    private final int column;
    private Random random;
    private List<Vertex> nodes = new ArrayList<>();

    public List<Vertex> getNodes() {
        return nodes;
    }


    public MapLogic(int maxSize, int column, boolean test) {
        this.totalGrids = maxSize;
        this.column = column;
        this.random = new Random(0);
        createMap(test);
    }
    public MapLogic(int maxSize, int column, boolean test, long seed) {
        this.totalGrids = maxSize;
        this.column = column;
        this.random = new Random(seed);
        createMap(test);
    }

    public int getTotalGrids() {
        return totalGrids;
    }

    public int getColumn() {
        return column;
    }

    public void createMap(boolean test) {
        if (!test) {
            createPlayMap();
        } else {
            createTestMap();
        }
    }


    private void createTestMap() {
        TileObject object;
        for (int i = 0; i < totalGrids; i++) {
            if (i % 4 == 0)
                object = TileObject.WALL;
            else if (i % 21 == 0)
                object = TileObject.POWERUP;
            else
                object = TileObject.WALKABLE;

            addGrid(i, object);
        }

    }

    private void createPlayMap() {
        TileObject object;
        for (int i = 0; i < totalGrids; i++) {
            if (this.random.nextInt(12) == 4)
                object = TileObject.WALL;
            else if (this.random.nextInt(40) == 2)
                object = TileObject.POWERUP;
            else
                object = TileObject.WALKABLE;

            addGrid(i, object);

        }
    }

    private void addGrid(int i, TileObject object) {
        Vertex location = new Vertex("Node_" + i, "Node_" + i, i, object);
        nodes.add(location);
    }

    public List<Vertex> getMap() {
        return this.nodes;
    }

    public Vertex getSpecificNode(int number) {
        List<Vertex> node = nodes.stream().filter(vertex -> vertex.getIdNumber() == number).collect(Collectors.toList());
        return node.get(0);
    }

    public List<Vertex> getAllNodesTouchedByPlayer(int playerNumber) {
        return nodes.stream().filter(vertex -> vertex.getTouchedBy() == playerNumber).collect(Collectors.toList());
    }
}
