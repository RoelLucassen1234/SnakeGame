package CommunicatorClient;

import Interface.IGameClient;
import Models.Communicator;
import Models.CommunicatorMessage;
import Models.Position;
import com.google.gson.Gson;
import javafx.geometry.Pos;

import java.util.Observable;
import java.util.Observer;

public class CommunicatorClientObserver implements Observer {

    private Communicator communicator;
    private final String[] properties = {"Ready", "Start", "Move", "Dies","End"};
    private Gson gson;
    private IGameClient game;

    public CommunicatorClientObserver(IGameClient game){
        communicator = CommunicatorClientWebSocket.getInstance();
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

    public void sendReady(int playerNr) {
        if (communicator != null) {
            String content = gson.toJson(playerNr);
            CommunicatorMessage message = new CommunicatorMessage();
            message.setProperty(properties[0]);
            message.setContent(content);
            communicator.update(message);
        }
    }

    public void sendPosition(int playerNr, int position){
        if (communicator != null) {
            Position playerposition = new Position(playerNr, position);
            String content = gson.toJson(playerposition);
            CommunicatorMessage message = new CommunicatorMessage();
            message.setProperty(properties[2]);
            message.setContent(content);
            communicator.update(message);
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        CommunicatorMessage message = (CommunicatorMessage) arg;
        String property = message.getProperty();
        String content = message.getContent();
        if(property.matches(properties[0])) {
            int playerNr = gson.fromJson(content, Integer.class);
            game.receiveReady(playerNr);
        }
        else if(property.matches(properties[1])){

        }
        else if(property.matches(properties[2])){
            Position position = gson.fromJson(content, Position.class);
game.receivePosition(position.getPlayerNr(), position.getPosition());
        }
        else if(property.matches(properties[3])){

        }
    }
}
