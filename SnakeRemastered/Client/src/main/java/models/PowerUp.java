package models;

import enums.PowerUpType;
import interfaces.Iplayer;
import javafx.scene.image.Image;


public abstract class PowerUp {
    protected Image image;
    protected PowerUpType powerUpType;

    public void update(Iplayer iplayer){}

    public PowerUpType getPowerUpType(){
        return this.powerUpType;
    }
}
