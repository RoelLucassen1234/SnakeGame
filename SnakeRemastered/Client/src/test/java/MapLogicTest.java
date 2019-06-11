import enums.TileObject;
import logica.MapLogic;
import models.Vertex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MapLogicTest {

    private MapLogic mapLogic;
    @BeforeEach
    public void setUp() {
       mapLogic = new MapLogic(900,30, true);
    }

    @Test
    public void CheckIfWallsExist(){
       Vertex vertex = null;
       int nodePosition = 4;

       vertex = mapLogic.getSpecificNode(nodePosition);

        Assertions.assertEquals(TileObject.WALL,vertex.getStatus());
    }

    @Test
    public void CheckIfWalkableExist(){
        Vertex vertex = null;
        int nodePosition = 3;

        vertex = mapLogic.getSpecificNode(nodePosition);

        Assertions.assertEquals(TileObject.WALKABLE,vertex.getStatus());
    }

    @Test
    public void CheckIfPowerUpExist(){
        Vertex vertex = null;
        int nodePosition = 21;

        vertex = mapLogic.getSpecificNode(nodePosition);

        Assertions.assertEquals(TileObject.POWERUP,vertex.getStatus());
    }

    @Test
    public void GetCertainNodesTouchedByPlayer(){
        int playerNr = 100;
        int nodePosition = 3;
        mapLogic.getSpecificNode(nodePosition).setTouchedBy(playerNr);
        nodePosition = 4;
        mapLogic.getSpecificNode(nodePosition).setTouchedBy(playerNr);

        List<Vertex> nodes = mapLogic.getAllNodesTouchedByPlayer(playerNr);

        Assertions.assertEquals(2,nodes.size());
        Assertions.assertEquals(nodes.get(0).getTouchedBy(), playerNr);
        Assertions.assertEquals(nodes.get(1).getTouchedBy(), playerNr);

    }

    @Test
    public void CreateMap(){
        mapLogic.getMap().clear();
        List<Vertex> nodes = mapLogic.getMap();

        Assertions.assertEquals(0, nodes.size());
        mapLogic.createMap(true);
        Assertions.assertNotEquals(0,mapLogic.getMap());
        Assertions.assertEquals(30,mapLogic.getColumn());
        Assertions.assertEquals(900,mapLogic.getTotalGrids());

    }
}
