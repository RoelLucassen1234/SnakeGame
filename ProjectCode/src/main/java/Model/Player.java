package Model;
import Enum.*;
public class Player {
    private int playerNumber;
    private int spawnPoint;
    private int currentPoint;
    private Direction direction;


    public Player(int playerNumber){
        this.playerNumber = playerNumber;
        spawnPoint = -1;
        direction = Direction.DOWN;
    }
    public int getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(int currentPoint) {
        this.currentPoint = currentPoint;
    }

    public Direction getDirection(){
        return direction;
    }
    public void setDirection(Direction direction){
        this.direction = direction;
    }

    public void setSpawnPoint(int spawnTile){
        this.spawnPoint = spawnTile;
        this.currentPoint = spawnPoint;
    }

    public int getSpawnPoint(){
        return this.spawnPoint;
    }
}
