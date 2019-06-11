package logica;

import enums.Direction;
import interfaces.IGameClient;
import interfaces.IPlayerLogic;
import models.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.Main;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerLogic implements IPlayerLogic {

    private final Logger log = LoggerFactory.getLogger(Main.class);
    private Player player;
    private Timer timer;
    private TimerTask task;
    private IGameClient movement;
    final private PlayerLogic playerInfo;
    private boolean playerAlive = true;
    private int movementspeed = 500;


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

        createTimer();
    }

    @Override
    public String colorReturn() {
        return player.getColor();
    }

    @Override
    public void setSpeed(int speed) {
        timer.purge();
        movementspeed = speed;
        createTimer();

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

    private void createTimer() {
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
                    log.info(e.getMessage());
                }
            }
        };
        timer.schedule(task, 500, movementspeed);
    }
}


