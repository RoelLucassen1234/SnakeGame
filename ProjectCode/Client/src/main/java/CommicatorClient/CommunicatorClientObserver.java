package CommicatorClient;

import Interface.IGameClient;
import Model.Communicator;
import Model.CommunicatorMessage;

import java.util.Observable;
import java.util.Observer;

public class CommunicatorClientObserver implements Observer {
    private CommunicatorClientWebsocket communicator;
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

        }
        else if(property.matches(properties[1])){

        }
        else if(property.matches(properties[2])){

        }
        else if(property.matches(properties[3])){

        }
    }
}
