package Controllers;

import Models.PlayerScore;
import javafx.scene.control.Label;
import loginClient.SnakeLoginClient;

public class ScoreController {

   public Label lbWins;
   public Label lbLosses;
   private SnakeLoginClient client = new SnakeLoginClient();

   public void setScore(String username){
       PlayerScore playerScore = client.getScore(username);
       lbWins.setText(Integer.toString(playerScore.getPlayerWins()));
       lbLosses.setText(Integer.toString(playerScore.getPlayerLoss()));

   }
}
