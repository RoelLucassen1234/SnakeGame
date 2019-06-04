import Logic.AiLogic;
import Logic.GameClient;
import Logic.MapLogic;
import Enum.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FullGameTest {
    private MapLogic map;
    private AiLogic logic;
    private GameClient client;

    @BeforeEach
    public void setUp() {
        client = new GameClient(true, 30, 30, null, true);
        client.getPlayer().setCurrentPoint(0);
        map = new MapLogic(900, 30, true);
        logic = new AiLogic(900, 30, map.getMap(), 0, client);
    }

    @Test
    public void RespawnOnWall() {
        int location = client.getPlayer().getCurrentLocation();

        client.setSpawnPoint(4);

        Assertions.assertEquals(client.getPlayer().getCurrentLocation(), location);
    }

    @Test
    public void RespawnOnPowerUp() {
        int location = client.getPlayer().getCurrentLocation();

        client.setSpawnPoint(21);

        Assertions.assertEquals(client.getPlayer().getCurrentLocation(), location);
    }

    @Test
    public void RespawnOnWalkable() {
        int location = client.getPlayer().getCurrentLocation();

        client.setSpawnPoint(3);

        Assertions.assertNotEquals(client.getPlayer().getCurrentLocation(), location);

    }

    @Test
    public void movePlayer(){
        int location = 3;
        client.setSpawnPoint(location);
        client.getPlayer().setDirection(Direction.DOWN);

        client.move(client.getPlayer());

        Assertions.assertNotEquals(location, client.getPlayer().getCurrentLocation());
    }




}
