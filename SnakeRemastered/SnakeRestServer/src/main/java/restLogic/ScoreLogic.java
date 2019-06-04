package restLogic;

import Models.GameResult;
import Models.PlayerScore;
import Models.User;
import restData.ScoreDal;

public class ScoreLogic {
    private ScoreDal iScoreDal;

    public ScoreLogic() {
        iScoreDal = new ScoreDal();
    }


    public boolean addbattleResult(GameResult result) {

        if (result.getUsername() != null)
            return this.iScoreDal.create(result);
        return false;
    }


    public PlayerScore getScoreboard(User user) {
        if (user.getPassword() != null && user.getUsername() != null)
            return iScoreDal.getGameResult(user);
        return null;
    }

}
