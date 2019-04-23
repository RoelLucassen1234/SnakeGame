package Model;

import Enum.*;
import javafx.scene.image.Image;


public abstract class PowerUp {
    protected Image image;
    protected PowerUpType powerUpType;

    public void update(){};

    public PowerUpType getPowerUpType(){
        return this.powerUpType;
    }
}
