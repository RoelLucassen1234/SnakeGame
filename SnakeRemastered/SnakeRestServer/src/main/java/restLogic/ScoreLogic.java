package restLogic;

import models.GameResult;
import models.PlayerScore;
import restData.ScoreDal;
import restInterface.IScoreDal;

import java.util.List;

public class ScoreLogic {
    private IScoreDal iScoreDal;

    public ScoreLogic() {
        iScoreDal = new ScoreDal();
    }


    public boolean addbattleResult(GameResult result) {
        return this.iScoreDal.create(result);
    }


    public PlayerScore getScoreboardOfUser(String user) {
        return iScoreDal.getGameResult(user);

    }

    public List<PlayerScore> getScoreboard() {
        return iScoreDal.getAllScoresFromUsers();
    }

}
