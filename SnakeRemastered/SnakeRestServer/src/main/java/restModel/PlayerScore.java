package restModel;

public class PlayerScore {
    int playerWins;
    int playerLoss;

    public PlayerScore(int playerWins, int playerLoss) {
        this.playerWins = playerWins;
        this.playerLoss = playerLoss;
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
}
