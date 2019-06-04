package Models;

public class GameResult {

    public GameResult(){

    }

    public GameResult(String username, boolean win) {
        this.username = username;
        this.win = win;
    }

    public boolean isWin() {
        return win;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    String username;
    boolean win;
}
