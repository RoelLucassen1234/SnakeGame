package models;

import enums.PowerUpType;
import interfaces.Iplayer;

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
