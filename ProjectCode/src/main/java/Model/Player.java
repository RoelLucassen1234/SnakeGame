package Model;
import Enum.*;
public class Player {
    private int playerNumber;
    private int spawnPoint;
    private int currentPoint;

    public int getLives() {
        return lives;
    }

    private int lives;
    private Direction direction;
    private String color = "FFFF00";

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public String getColor() {
        return color;
    }

    public Player(int playerNumber){
        this.playerNumber = playerNumber;
        spawnPoint = -1;
        direction = Direction.DOWN;
        lives = 3;
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

    public void removeLife(){
        lives--;
    }
}
