package Interface;

import Models.Vertex;

import java.io.IOException;
import java.util.List;

public interface IGridMain {
    void showPath(Vertex vertex, String color);
    void removeTerritory(List<Vertex> nodes);
    void drawPositionOpponent(int totalNumber);
    void goBack() throws IOException;
}
