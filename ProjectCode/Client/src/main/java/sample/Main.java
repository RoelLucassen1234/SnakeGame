package sample;

import Enum.*;
import Interface.IGridMain;
import Logic.GameClient;
import Model.Vertex;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application implements IGridMain {

    int numCols;
    private GridPane grid;
    private GameClient client;


    @Override
    public void start(Stage primaryStage) throws Exception {

        numCols = 50;
        int numRows = 50;

        ArrayList<String> input = new ArrayList<>();
        client = new GameClient(true, numCols, numRows, this);

        BooleanProperty[][] switches = new BooleanProperty[numCols][numRows];
        for (int y = 0; y < numRows; y++) {
            for (int x = 0; x < numCols; x++) {
                switches[x][y] = new SimpleBooleanProperty();
            }
        }

        grid = createGrid(switches);
        generateObjects(client.getObjects(TileObject.POWERUP));
        generateObjects(client.getObjects(TileObject.WALL));


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

        if (client.getPhase() == GamePhase.PREPERATION){
           if(client.setSpawnPoint(id)) {
               circle = new Circle(10, Color.GREEN);
               circle.visibleProperty().bind(cellswitch);
               cellswitch.set(!cellswitch.get());
               cell.getChildren().add(circle);
               cell.getStyleClass().add("cell");
               client.startGame();
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
                        node.setStyle("-fx-background-color:#9400D3 ;");
                    else
                        node.setStyle("-fx-background-color:#000000;");
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
                node.setStyle("-fx-background-color:#" + color);
            }
        }
    }

    public void removeTerritory(List<Vertex> nodes){
        for (Vertex node: nodes) {
            int x = (int) Math.floor((double) node.getIdNumber() / numCols);
            int y = node.getIdNumber() - (x * numCols);

            for (Node gridChild : grid.getChildren()) {
                if (GridPane.getColumnIndex(gridChild) == y && GridPane.getRowIndex(gridChild) == x) {
                    gridChild.setStyle("-fx-background-color:#" + "FFFFFF");
                }
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
