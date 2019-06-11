package controllers;

import Interface.IloginClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import loginClient.SnakeLoginClient;

import java.io.IOException;

public class RegisterController {

    public TextField tbUsername;
    public TextField tbPassword;
    public Button btnRegister;
    public Button btnBack;


    private IloginClient client = new SnakeLoginClient();

    @FXML
    public void registerUser(ActionEvent actionEvent) throws IOException{
        boolean user = client.register(tbUsername.getText(), tbPassword.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (user){
            alert.setTitle("Succesfully registered");
            alert.setHeaderText("You have succeeded");
           alert.showAndWait();
            Stage stage;
            stage = (Stage) btnRegister.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
            switchStage(root, stage);

        }else{
            alert.setTitle("Register Failed");
            alert.setContentText("Username already exists");
            alert.showAndWait();
        }
    }

    @FXML
    public void backToMenu(ActionEvent actionEvent) throws IOException{

    }

    private void switchStage(Parent root, Stage stage){
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
