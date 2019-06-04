package Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    public Label lbUsername;
    private String username;
    private Stage stage;

    @FXML
    private void initialize() {

        Platform.runLater(() -> {

            //do stuff

        });

    }

    public void setName(String username){
        lbUsername.setText(username);
        this.username = username;
    }


    @FXML
    public void goToScore(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ScoreBoard.fxml"));

        Parent root = fxmlLoader.load();
        ScoreController controller = fxmlLoader.<ScoreController>getController();
        controller.setScore(username);
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.show();


    }
}
