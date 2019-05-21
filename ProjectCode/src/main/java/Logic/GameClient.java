package Logic;

import Enum.Direction;
import Enum.GamePhase;
import Enum.TileObject;
import Interface.IGridMain;
import Interface.IGameClient;
import Interface.Iplayer;
import Model.Vertex;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GameClient implements IGameClient {
    private Iplayer player;
    private Iplayer opponent;

    private GamePhase phase;
    private MapLogic map;
    private IGridMain boardMap;
    private boolean singlePlayer;

    public GamePhase getPhase() {
        return phase;
    }
    public GameClient(boolean singlePlayer, int column1, int column2, IGridMain main) {
        this.singlePlayer = singlePlayer;
        if (singlePlayer) {
            map = new MapLogic((column1 * column2), column1);
            player = new PlayerLogic(0, this);
            boardMap = main;
        }
        phase = GamePhase.PREPERATION;
    }




    public List<Vertex> getObjects(TileObject object) {
        return map.getNodes().stream().filter(vertex -> vertex.getStatus().equals(object)).collect(Collectors.toList());
    }
    public boolean setSpawnPoint(int tileId) {

        if (map.getSpecificNode(tileId).getStatus() != TileObject.WALL && map.getSpecificNode(tileId).getStatus() != TileObject.POWERUP) {
            player.setSpawnPoint(tileId);
            return true;
        }

        return false;
    }
    public void startGame() {
        if (singlePlayer && player.getSpawnPoint() != -1) {
            Random random = new Random();
            List<Vertex> spawnpoints = getObjects(TileObject.WALKABLE);
            Vertex vertex = spawnpoints.get(random.nextInt(spawnpoints.size()));
            opponent = new AiLogic(map.getTotalGrids(), map.getColumn(), map.getNodes(), vertex.getIdNumber(), this);
            ((AiLogic) opponent).startGame();
            player.startGame();
        }
        phase = GamePhase.ONGOING;
    }
    @Override
    public void move(Iplayer player) {
        if (singlePlayer) {
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
            map.getSpecificNode(player.getCurrentLocation()).setStatus(TileObject.WALL);

            if (node.getStatus() == TileObject.TERRITORY || node.getStatus() == TileObject.WALL) {
                player.playerDies();
                boardMap.removeTerritory(map.getAllNodesTouchedByPlayer(player.getPlayerNumber()));
            } else {

                player.setCurrentSpawn(node.getIdNumber());
                node.setTouchedBy(player.getPlayerNumber());
                boardMap.showPath(node, player.colorReturn());
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
    @Override
    public List<Vertex> getNodes() {
        if (singlePlayer)
        return map.getNodes();
        else
            return null;

    }

    public void changePlayerDirection(String code) {
Direction direction = Direction.LEFT;
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
                getPlayer().setDirection(Direction.DOWN);
                break;
                default:
                   direction = getPlayer().getDirection();
                   break;

        }

        if (singlePlayer)
            getPlayer().setDirection(direction);
    }
}
