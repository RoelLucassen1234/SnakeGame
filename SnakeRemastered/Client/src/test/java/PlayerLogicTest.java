import Enum.Direction;
import Interface.IGridMain;
import Interface.Iplayer;
import Logic.GameClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerLogicTest {

    private GameClient gameClient;
    private Iplayer player;

    @BeforeEach
    public void setUp() {
        IGridMain movement =null;
        gameClient = new GameClient(true, 30, 30, movement, true);
        player = gameClient.getPlayerLogic();
    }

    @Test
    public void setSpawn() {
        int spawnpoint = 0;

        player.setSpawnPoint(spawnpoint);

        Assertions.assertEquals(0, player.getSpawnPoint());
    }

    @Test
    public void setDirection(){
        Direction direction = Direction.LEFT;
        Direction currentPlayerDirection = player.getDirection();

        player.setDirection(direction);

        Assertions.assertNotEquals(currentPlayerDirection, player.getSpawnPoint());

    }

    @Test
    public void setDirectionWithNul(){
        Direction direction = null;
        Direction currentPlayerDirection = player.getDirection();

        player.setDirection(direction);

        Assertions.assertEquals(currentPlayerDirection, player.getDirection());

    }

    @Test
    public void playerSetReady(){
        boolean ready = true;
        boolean playerReady = player.getReady();

        player.setReady(ready);

        Assertions.assertNotEquals(playerReady, player.getReady());
    }

    @Test
    public void PlayerLosesLive(){
        int playerLives = player.getPlayerLife();

        player.playerDies();

        Assertions.assertNotEquals(playerLives, player.getPlayerLife());
    }

    @Test
    public void movePlayer() throws InterruptedException {
        int spawnpoint = 0;
        player.setSpawnPoint(spawnpoint);
        player.setReady(true);
        player.startGame();
        Thread.sleep(600);
        int playercurrentposition = player.getCurrentLocation();
        player.playerDies();

        Assertions.assertNotEquals(playercurrentposition, spawnpoint);

    }

    @Test
    public void setMovement(){
        int movement = player.getMovementSpeed();
        int speed = 700;
        player.setReady(true);
        player.startGame();

        player.setSpeed(speed);
        player.playerDies();

        Assertions.assertNotEquals(movement, player.getMovementSpeed());

    }

}
