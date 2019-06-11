package models;

public class PlayerScore {
    int playerWins;
    int playerLoss;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    String playerName;

    public PlayerScore(int playerWins, int playerLoss) {
        this.playerWins = playerWins;
        this.playerLoss = playerLoss;
    }
    public PlayerScore(int playerWins, int playerLoss, String playerName) {
        this.playerWins = playerWins;
        this.playerLoss = playerLoss;
        this.playerName = playerName;
    }

    public int getPlayerWins() {
        return playerWins;
    }

    public PlayerScore() {
    }

    public void setPlayerWins(int playerWins) {
        this.playerWins = playerWins;
    }

    public int getPlayerLoss() {
        return playerLoss;
    }

    public void setPlayerLoss(int playerLoss) {
        this.playerLoss = playerLoss;
    }

    public String toString(){
        return playerName + " : wins: " + playerWins + " , losses: " + playerLoss;

    }
}
