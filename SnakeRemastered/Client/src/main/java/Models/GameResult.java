package Models;

public class GameResult {

    public GameResult(){

    }

    public GameResult(int playerNr, int win) {
        this.playerNr = playerNr;
        this.win = win;
    }

    public int isWin() {
        return win;
    }



    public void setWin(int win) {
        this.win = win;
    }

    public int getPlayerNr() {
        return playerNr;
    }

    private int playerNr;
    private int win;
}
