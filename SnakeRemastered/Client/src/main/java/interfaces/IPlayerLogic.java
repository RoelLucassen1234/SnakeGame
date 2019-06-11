package interfaces;

public interface IPlayerLogic extends Iplayer {
    void setReady(boolean ready);
    boolean getReady();
    int getPlayerLife();
}
