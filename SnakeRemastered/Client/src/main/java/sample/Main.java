package sample;

import Controllers.MenuController;
import Enum.GamePhase;
import Enum.TileObject;
import Interface.IGridMain;
import Logic.GameClient;
import Models.Vertex;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application implements IGridMain {

    int numCols;
    private GridPane grid;
    private Stage stage;
    private GameClient client;
    private final String backgroundColor = "-fx-background-color:#";
    private int playernr;
    private String username;
    private boolean singleplayer;

    public void isSingleplayer(boolean singleplayer, int playerNr, String username) {
        this.singleplayer = singleplayer;
        this.playernr = playerNr;
        this.username = username;

    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        numCols = 50;
        int numRows = 50;

        ArrayList<String> input = new ArrayList<>();
        client = new GameClient(singleplayer, numCols, numRows, this, playernr);


        BooleanProperty[][] switches = new BooleanProperty[numCols][numRows];
        for (int y = 0; y < numRows; y++) {
            for (int x = 0; x < numCols; x++) {
                switches[x][y] = new SimpleBooleanProperty();
            }
        }

        grid = createGrid(switches);
        generateObjects(client.getObjects(TileObject.POWERUP));
        generateObjects(client.getObjects(TileObject.WALL));
        generateObjects(client.getObjects(TileObject.WALKABLE));


        StackPane root = new StackPane(grid);
        Scene scene = new Scene(root, 1000, 1000);
        scene.getStylesheets().add("grid-with-borders.css");
        primaryStage.setScene(scene);


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                client.changePlayerDirection(keyEvent.getCode().toString());
            }
        });


        primaryStage.show();

    }


    private StackPane createCell(BooleanProperty cellSwitch, int id) {

        StackPane cell = new StackPane();
        cell.setOnMouseClicked(e -> showCircle(cell, cellSwitch, id));

        Circle circle = new Circle(10, Color.GREEN);

        circle.visibleProperty().bind(cellSwitch);

        cell.getChildren().add(circle);
        cell.getStyleClass().add("cell");
        return cell;
    }

    private void showCircle(StackPane cell, BooleanProperty cellswitch, int id) {

        cell.getChildren().clear();
        Circle circle;

        if (client.getPhase() == GamePhase.PREPERATION) {
            if (client.setSpawnPoint(id)) {
                circle = new Circle(10, Color.GREEN);
                circle.visibleProperty().bind(cellswitch);
                cellswitch.set(!cellswitch.get());
                cell.getChildren().add(circle);
                cell.getStyleClass().add("cell");

            }
        }
    }

    private void generateObjects(List<Vertex> grids) {
        for (Vertex vertex : grids) {
            //deel id door 10
            int x = (int) Math.floor((double) vertex.getIdNumber() / numCols);
            //verwijder y = y - (x * numCols)
            int y = vertex.getIdNumber() - (x * numCols);
            for (Node node : grid.getChildren()) {
                if (GridPane.getColumnIndex(node) == y && GridPane.getRowIndex(node) == x)
                    if (vertex.getStatus() == TileObject.POWERUP)
                        node.setStyle(backgroundColor + "9400D3;");
                    else if (vertex.getStatus() == TileObject.WALL) {
                        node.setStyle(backgroundColor + "000000;");
                    } else {
                        node.setStyle(backgroundColor + "ffffffff;");
                    }

            }
        }
    }


    private GridPane createGrid(BooleanProperty[][] switches) {

        int numCols = switches.length;
        int numRows = switches[0].length;
        int gridId = 0;

        GridPane grid = new GridPane();

        for (int x = 0; x < numCols; x++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }

        for (int y = 0; y < numRows; y++) {
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }

        for (int y = 0; y < numRows; y++) {

            for (int x = 0; x < numCols; x++) {
                grid.add(createCell(switches[x][y], gridId), x, y);
                gridId++;
            }
        }
        grid.getStyleClass().add("grid");
        return grid;
    }

    public void showPath(Vertex vertex, String color) {
        int x = (int) Math.floor((double) vertex.getIdNumber() / numCols);

        int y = vertex.getIdNumber() - (x * numCols);
        for (Node node : grid.getChildren()) {
            if (GridPane.getColumnIndex(node) == y && GridPane.getRowIndex(node) == x) {
                System.out.println(x + " " + y);
                node.setStyle(backgroundColor + color);
                break;
            }
        }
    }

    public void removeTerritory(List<Vertex> nodes) {
        for (Vertex node : nodes) {
            int x = (int) Math.floor((double) node.getIdNumber() / numCols);
            int y = node.getIdNumber() - (x * numCols);
            for (Node gridChild : grid.getChildren()) {
                if (GridPane.getColumnIndex(gridChild) == y && GridPane.getRowIndex(gridChild) == x) {
                    gridChild.setStyle(backgroundColor + "ffffffff");
                }
            }
        }
    }

    public void drawPositionOpponent(int totalnumber) {

        for (Node gridChild : grid.getChildren()) {
            int x = (int) Math.floor((double) totalnumber / numCols);
            int y = totalnumber - (x * numCols);
            if (GridPane.getColumnIndex(gridChild) == y && GridPane.getRowIndex(gridChild) == x) {
                gridChild.setStyle("-fx-background-color:#" + "ff0000");
                break;
            }
        }
    }

    public void goBack() throws IOException {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu.fxml"));

                Parent root = null;
                try {
                    root = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MenuController controller = fxmlLoader.<MenuController>getController();
                controller.setName(username, client.getPlayer().getPlayerNumber());
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
