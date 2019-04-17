package Logic;

import Interface.Iplayer;

public class GameClient {
    private Iplayer player;
    private Iplayer opponent;

    public GameClient(boolean singlePlayer){
        if (singlePlayer){
            player = new PlayerLogic();
            opponent = new AiLogic(10,10,null);
        }
    }
}
