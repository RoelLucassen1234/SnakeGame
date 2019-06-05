package Models;

public class GameResult {

    public GameResult(){

    }

    public GameResult(String username, int win) {
        this.username = username;
        this.win = win;
    }

    public int isWin() {
        return win;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWin(int win) {
        this.win = win;
    }

    String username;
    int win;
}
