package restInterface;

import Models.GameResult;
import Models.PlayerScore;

import java.util.List;

public interface IScoreDal {
    boolean create(GameResult user);
    PlayerScore getGameResult(String user);
    List<PlayerScore> getAllScoresFromUsers();
}
