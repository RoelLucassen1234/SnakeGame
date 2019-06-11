import enums.Direction;
import logica.AiLogic;
import logica.GameClient;
import logica.MapLogic;
import models.Vertex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AiLogicTest {
    private MapLogic map;
    private AiLogic logic;

    @BeforeEach
    public void setUp() {
        GameClient client = new GameClient(true,30,30,null,true);
        client.getPlayer().setCurrentPoint(0);
        map = new MapLogic(900, 30, true);
        logic = new AiLogic(900, 30, map.getMap(), 0, client);
    }

    @Test
    public void AiLogicSpawn() {
        int spawn = logic.getCurrentLocation();
        int newspawn = 30;

        logic.setCurrentPoint(newspawn);

        Assertions.assertNotEquals(spawn, logic.getCurrentLocation());
    }

    @Test
    public void FindPathToTarget() {
        logic.setCurrentPoint(1);
        logic.setDestination(2);
        List<Vertex> path;

        path = logic.calculatePath();

        Assertions.assertNotNull(path);
    }

    @Test
    public void SetDirection() {
        Direction direction = logic.getDirection();
        Direction newDirection = Direction.LEFT;

        logic.setDirection(newDirection);

        Assertions.assertEquals(newDirection, logic.getDirection());
        Assertions.assertNotEquals(direction, logic.getDirection());
    }

    @Test
    public void SetDirectionWithNullValue() {
        Direction direction = logic.getDirection();
        Direction newDirection = null;

        logic.setDirection(newDirection);

        Assertions.assertEquals(direction, logic.getDirection());

    }

    @Test
    public void setSpeed() {
        int currentSpeed = logic.getMovementSpeed();
        int newspeed = 500;

        logic.setSpeed(500);

        Assertions.assertEquals(newspeed, logic.getMovementSpeed());
        Assertions.assertNotEquals(currentSpeed, logic.getMovementSpeed());
    }

    @Test
    public void movement() throws InterruptedException {
        int spawnpoint = 3;
        logic.setDirection(Direction.DOWN);
        logic.setCurrentPoint(spawnpoint);


        logic.startGame();
        Thread.sleep(1000);
        logic.endGame();

        Assertions.assertNotEquals(spawnpoint, logic.getCurrentLocation());

    }


}
