package Models;

import Enum.PowerUpType;
import Interface.Iplayer;

public class SwitchBoost extends PowerUp{

    public SwitchBoost(){
        super.image = null;
        super.powerUpType = PowerUpType.NEUTRAL;

    }

    @Override
    public void update(Iplayer iplayer){
        iplayer.setSpeed(100);
    }
}
