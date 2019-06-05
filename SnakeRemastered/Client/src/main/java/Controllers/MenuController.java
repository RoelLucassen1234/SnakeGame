package Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class MenuController {
    public Label lbUsername;
    public Button btnScoreBoard;
    public Button btnMultiplayer;
    private String username;
    private Stage stage;
    private int playerNr;

    @FXML
    private void initialize() {

        Platform.runLater(() -> {

            //do stuff

        });

    }

    public void setName(String username, int playerNr){
        lbUsername.setText(username);
        this.username = username;
        this.playerNr = playerNr;
    }


    @FXML
    public void goToScore(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ScoreBoard.fxml"));

        Parent root = fxmlLoader.load();
        ScoreController controller = fxmlLoader.<ScoreController>getController();
        controller.setScore(username, playerNr);
        Scene scene = new Scene(root);
        stage = (Stage) btnScoreBoard.getScene().getWindow();
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    public void singleplayer(ActionEvent actionEvent) throws Exception {
      
        Main main = new Main();
        main.isSingleplayer(true, playerNr, username);
        main.start(new Stage());

    }

    @FXML
    public void multiplayer(ActionEvent actionEvent) throws Exception {
        Main main = new Main();
        main.isSingleplayer(false, playerNr, username);
        main.start(new Stage());

    }

}
