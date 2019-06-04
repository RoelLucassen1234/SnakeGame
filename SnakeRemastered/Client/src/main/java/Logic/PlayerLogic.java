package Logic;

import Enum.Direction;
import Interface.IGameClient;
import Interface.IPlayerLogic;
import Models.Player;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerLogic implements IPlayerLogic {
    private Player player;
    private Timer timer;
    private IGameClient movement;

    private int movementspeed = 200;
    final private PlayerLogic playerInfo;


    public PlayerLogic(int playernumber, IGameClient movement){
         player = new Player(playernumber);
      this.movement = movement;
      this.playerInfo = this;
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
        if (direction != null) {
            player.setDirection(direction);
        }
    }

    @Override
    public void setCurrentPoint(int currentSpawn) {
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
        }, 400, movementspeed);
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
        }, 400, movementspeed);
    }

    @Override
   public void playerDies(){
        player.removeLife();
      if (timer != null) {
          timer.cancel();
          timer.purge();
      }
   }

   @Override
   public int getPlayerNumber(){
        return player.getPlayerNumber();
   }

   public boolean getReady(){
        return player.getReady();
   }
   public void setReady(boolean ready){
        player.setReady(ready);
   }
  @Override
   public int getPlayerLife(){
       return player.getLives();
    }

    @Override
    public int getMovementSpeed(){
        return movementspeed;
    }
}


