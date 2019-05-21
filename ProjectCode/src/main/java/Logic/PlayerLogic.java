package Logic;

import Interface.IGameClient;
import Interface.Iplayer;
import Model.Player;
import Enum.*;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerLogic implements Iplayer {
    private Player player;
    private Timer timer;
    private IGameClient movement;

    public void setMovementspeed(int movementspeed) {
        this.movementspeed = movementspeed;
    }

    private int movementspeed = 200;
    final private PlayerLogic playerInfo;


    public PlayerLogic(int playernumber, IGameClient movement){
         player = new Player(playernumber);
      this.movement = movement;
      this.playerInfo = this;
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
    public void setDirection(Direction direction) {
        player.setDirection(direction);
    }

    @Override
    public void setCurrentSpawn(int currentSpawn) {
        player.setCurrentPoint(currentSpawn);
    }

    @Override
    public void startGame() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                movement.move(playerInfo);
            }
        }, 200, movementspeed);
    }

    @Override
    public String colorReturn() {
        return player.getColor();
    }

    @Override
    public void setSpeed(int speed) {
       timer.cancel();
       timer.purge();
       movementspeed = speed;
       timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                movement.move(playerInfo);
            }
        }, 200, movementspeed);
    }

    @Override
   public void playerDies(){
        player.removeLife();
        timer.cancel();
        timer.purge();
   }

   @Override
   public int getPlayerNumber(){
        return player.getPlayerNumber();
   }
    public int getPlayerLife(){
       return player.getLives();
    }
}


