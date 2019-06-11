package interfaces;

import models.PlayerScore;

import java.util.List;

public interface IScoreClient {
    PlayerScore getScore(String user);
    List<PlayerScore> getAllPlayerScores();
}
