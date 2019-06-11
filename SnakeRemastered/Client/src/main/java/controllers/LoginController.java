package controllers;


import interfaces.IloginClient;
import models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import loginClient.SnakeLoginClient;

import java.io.IOException;

public class LoginController{

    public TextField tbUsername;
    public TextField tbPassword;
    public Button registerButton;
    public Button logInButton;

    private Stage stage;
    private IloginClient client = new SnakeLoginClient();


    @FXML
    public void logInClick(ActionEvent actionEvent) throws IOException {

        User user = client.login(tbUsername.getText(), tbPassword.getText());
        if (user != null) {
           showMenu(user);
        }


    }

    @FXML
    public void goToRegister(ActionEvent actionEvent) throws IOException {
        stage = (Stage) registerButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Register.fxml"));
        switchStage(root, stage);


    }


    private void showMenu(User user) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu.fxml"));

        Parent root = fxmlLoader.load();
        MenuController controller = fxmlLoader.<MenuController>getController();
        controller.setName(user.getUsername(), user.getPlayerNr());
        Scene scene = new Scene(root);
        stage = (Stage) logInButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }


    private void switchStage(Parent root, Stage stage) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
