package Controllers;

import Interface.IScoreClient;
import Models.PlayerScore;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import loginClient.SnakeLoginClient;

public class ScoreController {

   public Label lbWins;
   public Label lbLosses;
   public ListView lvScores;
   private IScoreClient client = new SnakeLoginClient();

   public void setScore(String username){
       PlayerScore playerScore = client.getScore(username);
       lbWins.setText(Integer.toString(playerScore.getPlayerWins()));
       lbLosses.setText(Integer.toString(playerScore.getPlayerLoss()));
       lvScores.getItems().addAll(client.getAllPlayerScores());

   }
}
