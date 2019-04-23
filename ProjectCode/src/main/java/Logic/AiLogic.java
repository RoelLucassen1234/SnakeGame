package Logic;

import Enum.Direction;
import Enum.TileObject;
import Interface.IMovement;
import Interface.Iplayer;
import Model.Edge;
import Model.Graph;
import Model.Vertex;

import java.util.*;

public class AiLogic implements Iplayer {

    final private int totalGrids;
    final private int column;
final  private  AiLogic opponent;


    private int source;
    private int destination;
    private Timer timer;
    private IMovement movement;

    private DijkstraLogic dijstra;
    private List<Vertex> nodes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();

    private boolean forwardGrid = false;
    private boolean backwardsGrid = false;
    private boolean belowGrid = false;
    private boolean aboveGrid = false;

    private Direction direction;

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

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setCurrentSpawn(int currentSpawn) {
        source = currentSpawn;
    }


    //MAG NIET DE MAP AANMAKEN
    public AiLogic(int totalGrids, int column, List<Vertex> nodes, int spawnpoint, IMovement boardInformation) {
        this.totalGrids = totalGrids;
        this.column = column;
        this.random = new Random();
        this.nodes = nodes;
        setEdges(this.column);
        graph = new Graph(this.nodes, this.edges);
        setSpawnPoint(spawnpoint);
        this.movement = boardInformation;
        opponent = this;


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

    private int calculateWeight(int gridId) {
        int weight = 1;

        if (nodes.get(gridId).getStatus() == TileObject.POWERUP) {
            switch (nodes.get(gridId).getPowerUpType()) {
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

    public List<Vertex> calculatePath() {
        long startTime = System.currentTimeMillis();

        dijstra = new DijkstraLogic(graph);
        dijstra.execute(nodes.get(source));
        LinkedList<Vertex> path = dijstra.getPath(nodes.get(destination));

        for (Vertex vertex: path ) {
            System.out.println(vertex.getName());
        }
        long endTime = System.currentTimeMillis();
        long duration = ((endTime - startTime));
        //System.out.println(duration + " ms");
        return path;
    }

    @Override
    public void setSpawnPoint(int spawnPoint) {
        source = spawnPoint;
    }

    @Override
    public int getSpawnPoint() {
        return source;
    }

    @Override
    public int getCurrentLocation() {
        return source;
    }

    public void startGame() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
destination = movement.getPlayer().getCurrentLocation();

                int destinationId = -1;
                List<Vertex> vertexList = calculatePath();
                if (vertexList.size() > 1) {
                  destinationId = vertexList.get(1).getIdNumber();

                    if (source + 1 == destinationId){
                        direction = Direction.RIGHT;
                    }else if (source - 1 == destinationId){
                        direction = Direction.LEFT;
                    }else if(source - column == destinationId){
                        direction = Direction.UP;
                    }else if (source + column == destinationId ){
                        direction = Direction.DOWN;
                    }
                }
                System.out.println("Source =" + source + "||||" + destinationId);

                System.out.println(direction.toString());
                movement.move(opponent);
            }
        }, 200, 400);
    }

    public void endGame() {
        timer.cancel();
        timer.purge();
    }
}
