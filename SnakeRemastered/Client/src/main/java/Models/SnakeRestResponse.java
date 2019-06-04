package Models;

public class SnakeRestResponse {

    private boolean success;
    private User user;
    private PlayerScore playerScore;

    public boolean isSuccess() {
        return success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PlayerScore getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(PlayerScore playerScore) {
        this.playerScore = playerScore;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    public boolean getSuccess(){return success;}
}
