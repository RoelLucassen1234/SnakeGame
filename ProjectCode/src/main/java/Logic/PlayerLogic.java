package Logic;

import Interface.Iplayer;
import Model.Player;
import Enum.*;

public class PlayerLogic implements Iplayer {
    private Player player;


    public PlayerLogic(int playernumber){

         player = new Player(playernumber);
    }

    public void setSpawnPoint(int spawnPoint){
        player.setSpawnPoint(spawnPoint);
    }

    @Override
    public int getSpawnPoint() {
        return player.getSpawnPoint();
    }

    @Override
    public int getCurrentLocation() {
        return player.getCurrentPoint();
    }

    @Override
    public Direction getDirection() {
        return player.getDirection();
    }

    @Override
    public void setCurrentSpawn(int currentSpawn) {
        player.setCurrentPoint(currentSpawn);
    }

}
