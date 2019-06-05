package restLogic;

import Models.GameResult;
import Models.PlayerScore;
import restData.ScoreDal;
import restInterface.IScoreDal;

import java.util.List;

public class ScoreLogic {
    private IScoreDal iScoreDal;

    public ScoreLogic() {
        iScoreDal = new ScoreDal();
    }


    public boolean addbattleResult(GameResult result) {

        if (result.getUsername() != null)
            return this.iScoreDal.create(result);
        return false;
    }


    public PlayerScore getScoreboardOfUser(String user) {
        return iScoreDal.getGameResult(user);

    }

    public List<PlayerScore> getScoreboard() {
        return iScoreDal.getAllScoresFromUsers();
    }

}
