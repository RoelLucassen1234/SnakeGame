package Interface;

import Model.*;

import java.util.List;

public interface IGridMain {
    void showPath(Vertex vertex, String color);
    void removeTerritory(List<Vertex> nodes);
}
