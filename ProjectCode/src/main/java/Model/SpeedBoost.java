package Model;

import java.util.Random;
import Enum.*;

public class SpeedBoost extends PowerUp {

    public SpeedBoost(){
        Random random = new Random();
        super.image = null;
        switch (random.nextInt(1)){
            case 0:
                super.powerUpType = PowerUpType.FRIENDLY;
                break;
            case 1:
                super.powerUpType = PowerUpType.HOSTILE;
                break;
        }
    }

    @Override
    public void update(){

    }
}
