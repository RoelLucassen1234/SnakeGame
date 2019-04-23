package Logic;

import Interface.IGridMain;
import Interface.IMovement;
import Interface.IOpponentKnowledge;
import Interface.Iplayer;
import Enum.*;
import Model.Player;
import Model.Vertex;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GameClient implements IMovement {
    private Iplayer player;
    private Iplayer opponent;

    private GamePhase phase;
    private MapLogic map;

    private IGridMain boardMap;

    private boolean singlePlayer;

    public GamePhase getPhase() {
        return phase;
    }



    public GameClient(boolean singlePlayer, int column1, int column2, IGridMain main){
        this.singlePlayer = singlePlayer;
        if (singlePlayer){
            map = new MapLogic((column1 * column2), column1);
            player = new PlayerLogic(0);
            boardMap = main;
        }
        phase = GamePhase.PREPERATION;
    }

    public List<Vertex> getObjects(TileObject object){
        return map.getNodes().stream().filter(vertex -> vertex.getStatus().equals(object)).collect(Collectors.toList());
    }
    public boolean setSpawnPoint(int tileId) {

        if (map.getSpecificNode(tileId).getStatus() != TileObject.WALL && map.getSpecificNode(tileId).getStatus() != TileObject.POWERUP) {
            player.setSpawnPoint(tileId);
            return true;
         }

        return false;
    }
    public void startGame(){
        if (singlePlayer && player.getSpawnPoint() != -1){
            Random random = new Random();
            List<Vertex> spawnpoints = getObjects(TileObject.WALKABLE);
            Vertex vertex = spawnpoints.get(random.nextInt(spawnpoints.size()));
            opponent = new AiLogic(map.getTotalGrids(), map.getColumn(), map.getNodes(), vertex.getIdNumber(), this);
            ((AiLogic) opponent).startGame();
        }
        phase = GamePhase.ONGOING;
    }

    @Override
    public void move(Iplayer player) {
       int location = player.getCurrentLocation();
       Direction direction = player.getDirection();
       Vertex node = new Vertex("","s",2,null);

       switch (direction){
           case DOWN:
              node = map.getSpecificNode(location + map.getColumn());
               break;
           case LEFT:
               node = map.getSpecificNode(location  -1);
               break;
           case RIGHT:
               node = map.getSpecificNode(location + 1);
               break;
           case UP:
               node = map.getSpecificNode(location - map.getColumn());
               break;
       }
 player.setCurrentSpawn(node.getIdNumber());
       boardMap.showPath(node);
    }

    @Override
    public Iplayer getPlayer() {
        return player;
    }
}
