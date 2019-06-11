package models;

public class Position {
    int playerNr;

    public Position(int playerNr, int playerPosition) {
        this.playerNr = playerNr;
        this.playerPosition = playerPosition;
    }

    int playerPosition;

    public int getPlayerNr() {
        return playerNr;
    }

    public void setPlayerNr(int playerNr) {
        this.playerNr = playerNr;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(int playerPosition) {
        this.playerPosition = playerPosition;
    }
}
