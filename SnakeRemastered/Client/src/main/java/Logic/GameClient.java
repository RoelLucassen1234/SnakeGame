package Logic;

import CommunicatorClient.CommunicatorClientObserver;
import Enum.Direction;
import Enum.GamePhase;
import Enum.TileObject;
import Interface.IGameClient;
import Interface.IGridMain;
import Interface.IPlayerLogic;
import Interface.Iplayer;
import Models.Vertex;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GameClient implements IGameClient {
    private IPlayerLogic player;
    private Iplayer opponent;

    private GamePhase phase;
    private MapLogic map;
    private IGridMain boardMap;

    private boolean opponentReady = false;
    private boolean singlePlayer;
    private Random random = new Random();


    private CommunicatorClientObserver clientObserver = null;

    public GamePhase getPhase() {
        return phase;
    }

    public GameClient(boolean singlePlayer, int column1, int column2, IGridMain main) {
        this.singlePlayer = singlePlayer;
        player = new PlayerLogic(random.nextInt(10000), this);
        if (singlePlayer) {
            map = new MapLogic((column1 * column2), column1, false);
            boardMap = main;
            phase = GamePhase.PREPERATION;
        } else {
            clientObserver = new CommunicatorClientObserver(this);
            map = new MapLogic((column1 * column2), column1, false);
            boardMap = main;
            phase = GamePhase.PREPERATION;

        }
    }

    public GameClient(boolean singlePlayer, int column1, int column2, IGridMain main, boolean test) {
        this.singlePlayer = singlePlayer;
        player = new PlayerLogic(100, this);
        if (singlePlayer) {
            map = new MapLogic((column1 * column2), column1, test);
            boardMap = main;
            phase = GamePhase.PREPERATION;
        }
    }


    public List<Vertex> getObjects(TileObject object) {
            return map.getNodes().stream().filter(vertex -> vertex.getStatus().equals(object)).collect(Collectors.toList());
    }

    public boolean setSpawnPoint(int tileId) {

        if (map.getSpecificNode(tileId).getStatus() != TileObject.WALL && map.getSpecificNode(tileId).getStatus() != TileObject.POWERUP) {
            player.setCurrentPoint(tileId);
            if (singlePlayer) {
                startGame();
                return true;
            } else {
                player.setReady(true);
                clientObserver.sendReady(getPlayer().getPlayerNumber());

                return true;
            }
        }
        return false;
    }

    public void startGame() {
        if (singlePlayer && player.getCurrentLocation() != -1) {
            random = new Random();
            List<Vertex> spawnpoints = getObjects(TileObject.WALKABLE);
            Vertex vertex = spawnpoints.get(random.nextInt(spawnpoints.size()));
            opponent = new AiLogic(map.getTotalGrids(), map.getColumn(), map.getNodes(), vertex.getIdNumber(), this);
            ((AiLogic) opponent).startGame();
            player.startGame();
        } else if (!singlePlayer) {
            player.startGame();
        }
        phase = GamePhase.ONGOING;
    }

    @Override
    public void move(Iplayer player) {

        int location = player.getCurrentLocation();
        Direction direction = player.getDirection();
        Vertex node = new Vertex("", "s", 2, null);

        switch (direction) {
            case DOWN:
                node = map.getSpecificNode(location + map.getColumn());
                break;
            case LEFT:
                node = map.getSpecificNode(location - 1);
                break;
            case RIGHT:
                node = map.getSpecificNode(location + 1);
                break;
            case UP:
                node = map.getSpecificNode(location - map.getColumn());
                break;
        }

        {
            if (node.getStatus() == TileObject.TERRITORY || node.getStatus() == TileObject.WALL) {
                player.playerDies();
                if (boardMap != null) {
                    boardMap.removeTerritory(map.getAllNodesTouchedByPlayer(player.getPlayerNumber()));
                }

                if (!singlePlayer) {

                }
            } else {
                map.getSpecificNode(player.getCurrentLocation()).setStatus(TileObject.WALL);
                player.setCurrentPoint(node.getIdNumber());
                node.setTouchedBy(player.getPlayerNumber());
                if (boardMap != null) {
                    boardMap.showPath(node, player.colorReturn());
                }

                if (!singlePlayer) {
                    clientObserver.sendPosition(player.getPlayerNumber(), node.getIdNumber());
                }

                if (node.getStatus() == TileObject.POWERUP) {
                    node.getPowerUp().update(player);
                }
            }
        }
    }


    @Override
    public Iplayer getPlayer() {
        return player;
    }


    public void changePlayerDirection(String code) {
        Direction direction;
        switch (code) {
            case "LEFT":
                direction = Direction.LEFT;
                break;
            case "RIGHT":
                direction = Direction.RIGHT;
                break;
            case "UP":
                direction = Direction.UP;
                break;
            case "DOWN":
                direction = (Direction.DOWN);
                break;
            default:
                direction = getPlayer().getDirection();
                break;

        }
        getPlayer().setDirection(direction);

    }


    public void receiveReady(int playerNumber) {
        if (player.getPlayerNumber() != playerNumber)
            opponentReady = true;
        if (player.getReady() && opponentReady) {
            startGame();
        }
    }

    public void receivePosition(int Playernumber, int position) {
        if (player.getPlayerNumber() != Playernumber) {
            Vertex vertex = map.getSpecificNode(position);
            vertex.setTouchedBy(Playernumber);
            vertex.setStatus(TileObject.WALL);
            boardMap.drawPositionOpponent(position);
        }
    }

    public IPlayerLogic getPlayerLogic() {
        return player;
    }

    public Iplayer getAi(){
        return opponent;
    }
}
