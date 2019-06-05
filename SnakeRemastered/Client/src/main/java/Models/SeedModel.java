package Models;

public class SeedModel {
    public SeedModel(int playerNr, int seed) {
        this.playerNr = playerNr;
        this.seed = seed;
    }

    public int getPlayerNr() {
        return playerNr;
    }

    public void setPlayerNr(int playerNr) {
        this.playerNr = playerNr;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    int playerNr;
    int seed;

    public SeedModel(){

    }

}
