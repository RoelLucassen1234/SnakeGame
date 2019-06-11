package logica;

import enums.Direction;
import Interface.IGameClient;
import Interface.IPlayerLogic;
import models.Player;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerLogic implements IPlayerLogic {
    private Player player;
    private Timer timer;
    private TimerTask task;
    private IGameClient movement;
    private boolean playerAlive = true;

    private int movementspeed = 500;
    final private PlayerLogic playerInfo;


    public PlayerLogic(int playernumber, IGameClient movement) {
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

        CreateTimer();
    }

    @Override
    public String colorReturn() {
        return player.getColor();
    }

    @Override
    public void setSpeed(int speed) {
        timer.purge();
        movementspeed = speed;
        CreateTimer();

    }

    @Override
    public void playerDies() {
        player.removeLife();
        if (timer != null) {
            task.cancel();
            timer.cancel();
            timer.purge();
            timer = null;
            playerAlive = false;
        }
    }

    @Override
    public int getPlayerNumber() {
        return player.getPlayerNumber();
    }

    public boolean getReady() {
        return player.getReady();
    }

    public void setReady(boolean ready) {
        player.setReady(ready);
    }

    @Override
    public int getPlayerLife() {
        return player.getLives();
    }

    @Override
    public int getMovementSpeed() {
        return movementspeed;
    }

    public boolean isPlayerAlive() {
        return playerAlive;
    }

    private void CreateTimer() {
        if (task != null) {
            task.cancel();
            task = null;
        }
        task = new TimerTask() {
            @Override
            public void run() {
                try {
                    if (playerAlive)
                        movement.move(playerInfo);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(task, 500, movementspeed);
    }
}


