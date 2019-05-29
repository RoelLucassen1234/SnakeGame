package restLogic;

import restData.ScoreDal;
import restModel.GameResult;
import restModel.PlayerScore;
import restModel.User;

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
