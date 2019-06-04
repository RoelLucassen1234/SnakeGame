package restLogic;

import Models.GameResult;
import Models.PlayerScore;
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


    public PlayerScore getScoreboard(String user) {
            return iScoreDal.getGameResult(user);

    }

}
