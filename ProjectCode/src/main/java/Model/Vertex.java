package Model;

import Enum.*;

import java.util.Random;

public class Vertex {
    final private String id;
    private TileObject status;
    private PowerUp powerUp;
    final private int idNumber;
    final private String name;


    public TileObject getStatus() {
        return status;
    }
    public void setStatus(TileObject status) {
        this.status = status;
    }
    public int getIdNumber() {
        return idNumber;
    }

    public Vertex(String id, String name, int idnumber, TileObject object) {
        this.id = id;
        this.name = name;
        this.idNumber = idnumber;
        this.status = object;

        if (this.status == TileObject.POWERUP){
            Random random = new Random();
            switch (random.nextInt(2)){
                case 0:
                    powerUp = new ReverseBoost();
                    break;
                case 1:
                    powerUp = new SpeedBoost();
                    break;
                case 2:
                    powerUp = new SwitchBoost();
                    break;
                    default:
                        System.out.println("WARNING");
                        break;
            }
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PowerUpType getPowerUpType(){
        return powerUp.getPowerUpType();
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return name;
    }
}
