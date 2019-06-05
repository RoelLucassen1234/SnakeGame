package Controllers;

import Interface.IScoreClient;
import Models.PlayerScore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import loginClient.SnakeLoginClient;

import java.io.IOException;

public class ScoreController {

   public Label lbWins;
   public Label lbLosses;
   public Button btnBack;
   private Stage stage;
   private String username;
   private int playerNr;
   public ListView lvScores;
   private IScoreClient client = new SnakeLoginClient();

   public void setScore(String username, int playerNr){
       this.username = username;
       this.playerNr = playerNr;
       PlayerScore playerScore = client.getScore(username);
       lbWins.setText(Integer.toString(playerScore.getPlayerWins()));
       lbLosses.setText(Integer.toString(playerScore.getPlayerLoss()));
       lvScores.getItems().addAll(client.getAllPlayerScores());

   }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu.fxml"));
        Parent root = fxmlLoader.load();
        MenuController controller = fxmlLoader.<MenuController>getController();
        controller.setName(username, playerNr);
        Scene scene = new Scene(root);
        stage = (Stage) btnBack.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
