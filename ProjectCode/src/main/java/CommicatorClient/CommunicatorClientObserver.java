package CommicatorClient;

import Interface.IGameClient;
import Model.Communicator;
import Model.CommunicatorMessage;
import com.google.gson.Gson;

import java.util.Observable;
import java.util.Observer;

public class CommunicatorClientObserver implements Observer {
    private Communicator communicator;
    private final String[] properties = {"SetSpawn", "Start", "Move", "Dies","End"};
    private Gson gson;
    private IGameClient game;

    public CommunicatorClientObserver(IGameClient game){
        communicator = CommunicatorClientWebsocket.getInstance();
        communicator.addObserver(this);

        // Establish connection with server
        communicator.start();

        // Register properties to be published
        for (String property : properties) {
            communicator.register(property);
            communicator.subscribe(property);
        }
        gson = new Gson();

        this.game = game;
    }


    @Override
    public void update(Observable o, Object arg) {
        CommunicatorMessage message = (CommunicatorMessage) arg;
        String property = message.getProperty();
        String content = message.getContent();
        if(property.matches(properties[0])) {
            AttackInfo attackInfo = gson.fromJson(content, AttackInfo.class);
            game.receivedAttack(attackInfo.getPlayerNr(), attackInfo.getPosX(), attackInfo.getPosY());
            game.reloadGUI();
        }
        else if(property.matches(properties[1])){
            int playerNr = gson.fromJson(content, Integer.class);
            game.notifyWhenReady(playerNr);
        }
        else if(property.matches(properties[2])){
            AttackResult attackResult = gson.fromJson(content, AttackResult.class);
            game.receivedAttackResult(attackResult.getPlayerNr(), attackResult.getShotType(), attackResult.getShip(), attackResult.getPosX(), attackResult.getPosY());
        }
        else if(property.matches(properties[3])){
            ReadyResult readyResult = gson.fromJson(content, ReadyResult.class);
            game.receivedReadyResult(readyResult.getPlayerNr(), readyResult.getPlayerName(), readyResult.getBoard());
        }
    }
}
