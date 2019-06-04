package Models;

import Enum.PowerUpType;
import Interface.Iplayer;

import java.util.Random;

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
                default:
                    super.powerUpType = PowerUpType.FRIENDLY;
                    break;
        }
    }

    @Override
    public void update(Iplayer iplayer){
        iplayer.setSpeed(300);

    }
}
