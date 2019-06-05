package Models;

public class User {
    private String username;

    public int getPlayerNr() {
        return playerNr;
    }

    public void setPlayerNr(int playerNr) {
        this.playerNr = playerNr;
    }

    private int playerNr;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(String username, int playerNr) {
        this.username = username;
        this.playerNr = playerNr;
        this.password = null;
    }
    public User(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
}
