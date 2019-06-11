package models;

import enums.Direction;

public class Player {
    private int playerNumber;
    private int currentPoint;

    public int getLives() {
        return lives;
    }

    private int lives;
    private Direction direction;
    private String color = "FFFF00";
    private boolean isReady;

    public boolean getReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }


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
        direction = Direction.DOWN;
        lives = 3;
        isReady = false;
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



    public void removeLife(){
        lives--;
    }
}
