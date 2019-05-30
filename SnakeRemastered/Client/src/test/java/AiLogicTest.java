import Logic.AiLogic;
import Logic.MapLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AiLogicTest {
    private MapLogic map;
    private AiLogic logic;
    @BeforeEach
    public void setUp() {
        map = new MapLogic(900,30, true);
        logic = new AiLogic(900, 30, map.getMap(),0,null);
    }

    @Test
    public void AiLogicSpawn(){

    }

    @Test
    public void FindPathToTarget(){

    }

    @Test
    public void SetDirection(){

    }

    @Test
    public void SetDirectionWithNullValue(){

    }

    @Test
    public void setSpeed(){

    }


}
