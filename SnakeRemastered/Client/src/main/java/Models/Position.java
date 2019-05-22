package Models;

public class Position {
    int playerNr;

    public Position(int playerNr, int position) {
        this.playerNr = playerNr;
        this.position = position;
    }

    int position;

    public int getPlayerNr() {
        return playerNr;
    }

    public void setPlayerNr(int playerNr) {
        this.playerNr = playerNr;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
