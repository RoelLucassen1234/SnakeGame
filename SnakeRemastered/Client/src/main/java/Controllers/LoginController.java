package Controllers;


import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import loginClient.SnakeLoginClient;

import java.io.IOException;

public class LoginController {
    public Label titleLoginLabel;
    public Label userNameLabel;
    public Label passWordLabel;
    public TextField tbUsername;
    public TextField tbPassword;
    public PasswordField passwordField;
    public Button registerButton;
    public Button logInButton;

    private Stage stage;
    private SnakeLoginClient client = new SnakeLoginClient();


    @FXML
    public void logInClick(ActionEvent actionEvent) throws IOException {

        User user = client.login(tbUsername.getText(), tbPassword.getText());
        if (user != null) {
            stage = (Stage) logInButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/Menu.fxml"));
            switchStage(root, stage);
        }


    }

    @FXML
    public void goToRegister(ActionEvent actionEvent) throws IOException {
            stage = (Stage) registerButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/Register.fxml"));
            switchStage(root, stage);


    }


    private void switchStage(Parent root, Stage stage){
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
