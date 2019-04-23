package Logic;

import Interface.Iplayer;
import Model.Edge;
import Model.Graph;
import Enum.*;
import Model.Vertex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AiLogic implements Iplayer {

    final private int totalGrids;
    final private int column;

    private int source;
    private int destination;

    private DijkstraLogic dijstra;
    private List<Vertex> nodes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();

    private boolean forwardGrid = false;
    private boolean backwardsGrid = false;
    private boolean belowGrid = false;
    private boolean aboveGrid = false;

    private Random random;
    private Graph graph;


    public List<Vertex> getNodes() {
        return nodes;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getColumn() {
        return column;
    }



    //MAG NIET DE MAP AANMAKEN
    public AiLogic(int totalGrids, int column, List<Vertex> nodes) {
        this.totalGrids = totalGrids;
        this.column = column;
        this.random = new Random();
        this.nodes = nodes;
        setEdges(this.column);
        graph = new Graph(this.nodes, this.edges);


    }

    private void addLane(String laneId, int sourceLocNo, int destLocNo, int duration) {
        Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
    }

    private void setEdges(int column) {


        for (int i = 0; i < totalGrids; i++) {

            if ((i + 1) % column == 0 && i != 0)
                forwardGrid = true;

            if (i + 1 < totalGrids)
                if (nodes.get(i + 1).getStatus() == TileObject.WALL)
                    forwardGrid = true;

            if (i + column > totalGrids || i + column >= totalGrids)
                belowGrid = true;

            if (i + column < totalGrids)
                if (nodes.get(i + column).getStatus() == TileObject.WALL)
                    belowGrid = true;


            if (i % column == 0 || i == 0)
                backwardsGrid = true;

            if (i - 1 > 0 && i % column != 0)
                if (nodes.get(i - 1).getStatus() == TileObject.WALL)
                    backwardsGrid = true;

            if (i - column < 0)
                aboveGrid = true;

            if (i - column > 0)
                if (nodes.get(i - column).getStatus() == TileObject.WALL)
                    aboveGrid = true;


            if (!forwardGrid) {

                addLane("Lane" + i, i, i + 1, calculateWeight(i + 1));
            }
            if (!belowGrid) {

                addLane("Lane" + i, i, i + column, calculateWeight(i + column));
            }
            if (!backwardsGrid) {

                addLane("Lane" + i, i, i - 1, calculateWeight(i - 1));
            }
            if (!aboveGrid) {
                addLane("Lane" + i, i, i - column, calculateWeight(i - column));
            }

            belowGrid = false;
            aboveGrid = false;
            forwardGrid = false;
            backwardsGrid = false;
        }

    }

    private int calculateWeight(int gridId){
        int weight = 1;

        if (nodes.get(gridId).getStatus() == TileObject.POWERUP){
            switch (nodes.get(gridId).getPowerUpType()){
                case HOSTILE:
                    weight = -1;
                    break;
                case FRIENDLY:
                    weight = -1;
                    break;
                case NEUTRAL:
                    weight = -1;
                    break;
                default:
                    weight = 1;
                    break;
            }
        }

        return weight;

    }

    public List<Vertex> calculatePath(){
        long startTime = System.currentTimeMillis();

        dijstra = new DijkstraLogic(graph);
        dijstra.execute(nodes.get(source));
        LinkedList<Vertex> path = dijstra.getPath(nodes.get(destination));

//        for (Vertex vertex: path ) {
//            System.out.println(vertex.getName());
//        }
        long endTime = System.currentTimeMillis();
        long duration = ((endTime - startTime));
        System.out.println(duration + " ms");
        return path;
    }

}
