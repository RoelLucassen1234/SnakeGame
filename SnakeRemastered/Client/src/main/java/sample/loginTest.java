package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class loginTest extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root = (AnchorPane) FXMLLoader.load(Main.class.getResource("/Login.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
