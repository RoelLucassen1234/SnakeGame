package Logic;

import Interface.Iplayer;

public class GameClient {
    private Iplayer player;
    private Iplayer opponent;

    private MapLogic map;

    public GameClient(boolean singlePlayer, int column1, int column2){
        if (singlePlayer){
            map = new MapLogic((column1 * column2), column1);
            player = new PlayerLogic();
            opponent = new AiLogic((column1 * column2),column1,map.getMap());
        }
    }




}
