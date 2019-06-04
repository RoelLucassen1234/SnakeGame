package Logic;

import Enum.Direction;
import Enum.TileObject;
import Interface.IGameClient;
import Interface.Iplayer;
import Models.Edge;
import Models.Graph;
import Models.Player;
import Models.Vertex;

import java.util.*;

public class AiLogic implements Iplayer {

    final private int totalGrids;
    final private int column;
    private int destination;

    private Player player;
    private int movementspeed = 200;

    final private AiLogic opponent;
    private IGameClient movement;
    private DijkstraLogic dijstra;

    final private String color = "ff0000";

    private Timer timer;
    private List<Vertex> nodes ;
    private List<Edge> edges = new ArrayList<>();

    private boolean forwardGrid = false;
    private boolean backwardsGrid = false;
    private boolean belowGrid = false;
    private boolean aboveGrid = false;

    private Graph graph;


    public AiLogic(int totalGrids, int column, List<Vertex> nodes, int spawnpoint, IGameClient boardInformation) {
        this.totalGrids = totalGrids;
        this.column = column;
        this.nodes = nodes;
        setEdges(this.column);
        graph = new Graph(this.nodes, this.edges);
        player = new Player(1);
        setCurrentPoint(spawnpoint);
        this.movement = boardInformation;
        opponent = this;


    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public Direction getDirection() {
        return player.getDirection();
    }

    @Override
    public void setDirection(Direction direction) {
       if (direction != null) {
           player.setDirection(direction);
       }
    }

    @Override
    public void setCurrentPoint(int currentSpawn) {
        player.setCurrentPoint(currentSpawn);
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
                else if (nodes.get(i + 1).getStatus() == TileObject.TERRITORY) {
                    forwardGrid = true;
                }

            if (i + column > totalGrids || i + column >= totalGrids)
                belowGrid = true;

            if (i + column < totalGrids)
                if (nodes.get(i + column).getStatus() == TileObject.WALL)
                    belowGrid = true;
                else if (nodes.get(i + column).getStatus() == TileObject.TERRITORY) {
                    belowGrid = true;
                }


            if (i % column == 0 || i == 0)
                backwardsGrid = true;

            if (i - 1 > 0 && i % column != 0)
                if (nodes.get(i - 1).getStatus() == TileObject.WALL)
                    backwardsGrid = true;
                else if (nodes.get(i - 1).getStatus() == TileObject.TERRITORY) {
                    forwardGrid = true;
                }

            if (i - column < 0)
                aboveGrid = true;

            if (i - column > 0)
                if (nodes.get(i - column).getStatus() == TileObject.WALL)
                    aboveGrid = true;
                else if (nodes.get(i - column).getStatus() == TileObject.TERRITORY) {
                    forwardGrid = true;
                }


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


        setEdges(this.column);
        graph = new Graph(this.nodes, this.edges);
        dijstra = new DijkstraLogic(graph);
        dijstra.execute(nodes.get(player.getCurrentPoint()));
        LinkedList<Vertex> path = dijstra.getPath(nodes.get(destination));
        edges.clear();

        return path;
    }

    @Override
    public int getCurrentLocation() {
        return player.getCurrentPoint();
    }

    public void startGame() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {


               if (movement != null)
                destination = movement.getPlayer().getCurrentLocation();
                int destinationId = -1;
                List<Vertex> vertexList = calculatePath();
               if (vertexList != null)
                if (vertexList.get(1) != null) {
                        destinationId = vertexList.get(1).getIdNumber();
                        if (player.getCurrentPoint() + 1 == destinationId) {
                            player.setDirection(Direction.RIGHT);
                        } else if (player.getCurrentPoint() - 1 == destinationId) {
                            player.setDirection(Direction.LEFT);
                        } else if (player.getCurrentPoint() - column == destinationId) {
                            player.setDirection(Direction.UP);
                        } else if (player.getCurrentPoint() + column == destinationId) {
                            player.setDirection(Direction.DOWN);
                        }

                    movement.move(opponent);
                }
            }
        }, 500, movementspeed);
    }

    @Override
    public String colorReturn() {
        return getColor();
    }

    @Override
    public void setSpeed(int speed) {
        if (timer != null)
            endGame();

        movementspeed = speed;
        startGame();
    }

    @Override
    public int getPlayerNumber() {
        return 1;
    }

    @Override
    public void playerDies() {
        endGame();
        player.removeLife();
    }

    @Override
    public int getMovementSpeed() {
        return movementspeed;
    }
    public String getColor() {
        return color;
    }

    public void endGame() {
        timer.cancel();
        timer.purge();
    }
}
