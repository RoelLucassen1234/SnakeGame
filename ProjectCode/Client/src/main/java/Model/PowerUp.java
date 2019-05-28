package Model;

import Interface.Iplayer;
import javafx.scene.image.Image;
import Enum.*;


public abstract class PowerUp {
    protected Image image;
    protected PowerUpType powerUpType;

    public void update(Iplayer iplayer){}

    public PowerUpType getPowerUpType(){
        return this.powerUpType;
    }
}
