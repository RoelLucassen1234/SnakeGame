package Models;

import Enum.PowerUpType;
import Interface.Iplayer;

public class ReverseBoost extends PowerUp {

    public ReverseBoost(){
        super.image = null;
        super.powerUpType = PowerUpType.HOSTILE;
    }

    @Override
    public void update(Iplayer iplayer){
        iplayer.setSpeed(100);
    }
}
