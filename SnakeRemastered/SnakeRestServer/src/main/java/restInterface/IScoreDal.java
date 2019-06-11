package restInterface;

import models.GameResult;
import models.PlayerScore;

import java.util.List;

public interface IScoreDal {
    boolean create(GameResult user);
    PlayerScore getGameResult(String user);
    List<PlayerScore> getAllScoresFromUsers();
}
