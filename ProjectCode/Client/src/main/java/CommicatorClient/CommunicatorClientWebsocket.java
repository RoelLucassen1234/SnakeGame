package CommicatorClient;

import Interface.ICommunicator;
import Model.Communicator;
import Model.CommunicatorMessage;
import Model.CommunicatorWebSocketMessage;
import Model.CommunicatorWebSocketMessageOperation;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientEndpoint
public class CommunicatorClientWebsocket extends Communicator {


    private final Logger log = LoggerFactory.getLogger(CommunicatorClientWebsocket.class);

    // Singleton
    private static CommunicatorClientWebsocket instance = null;

    /**
     * The local websocket uri to connect to.
     */

    private static final String uri = "ws://192.168.222.5:8095/communicator/";

    private Session session;

    private String message;

    private Gson gson;

    // Status of the webSocket client
    boolean isRunning = false;

    // Private constructor (singleton pattern)
    private CommunicatorClientWebsocket() {
        gson = new Gson();
    }

    /**
     * Get singleton instance of this class.
     * Ensure that only one instance of this class is created.
     * @return instance of client web socket
     */
    public static CommunicatorClientWebsocket getInstance() {
        if (instance == null) {
            instance = new CommunicatorClientWebsocket();
        }
        return instance;
    }

    /**
     *  Start the connection.
     */
    @Override
    public void start() {
        log.info("[WebSocket Client start connection]");
        if (!isRunning) {
            startClient();
            isRunning = true;
        }
    }

    @Override
    public void stop() {
        log.info("[WebSocket Client stop]");
        if (isRunning) {
            stopClient();
            isRunning = false;
        }
    }

    @OnOpen
    public void onWebSocketConnect(Session session){
        log.info("[WebSocket Client open session] {}", session.getRequestURI());
        this.session = session;
    }

    @OnMessage
    public void onWebSocketText(String message, Session session){
        this.message = message;
        log.info("[WebSocket Client message received] {}", message);
        processMessage(message);
    }

    @OnError
    public void onWebSocketError(Session session, Throwable cause) {
        log.info("[WebSocket Client connection error] {}", cause);
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason){
        log.info("[WebSocket Client close session] {}", session.getRequestURI());
        log.info(" for reason {}", reason);
        session = null;
    }

    @Override
    public void register(String property) {
        CommunicatorWebSocketMessage webSocketMessage = new CommunicatorWebSocketMessage();
        webSocketMessage.setOperation(CommunicatorWebSocketMessageOperation.REGISTERPROPERTY);
        webSocketMessage.setProperty(property);
        sendMessageToServer(webSocketMessage);
    }

    @Override
    public void unregister(String property) {
        CommunicatorWebSocketMessage webSocketMessage = new CommunicatorWebSocketMessage();
        webSocketMessage.setOperation(CommunicatorWebSocketMessageOperation.UNREGISTERPROPERTY);
        webSocketMessage.setProperty(property);
        sendMessageToServer(webSocketMessage);
    }

    @Override
    public void subscribe(String property) {
        CommunicatorWebSocketMessage webSocketMessage = new CommunicatorWebSocketMessage();
        webSocketMessage.setOperation(CommunicatorWebSocketMessageOperation.SUBSCRIBETOPROPERTY);
        webSocketMessage.setProperty(property);
        sendMessageToServer(webSocketMessage);
    }

    @Override
    public void unsubscribe(String property) {
        CommunicatorWebSocketMessage webSocketMessage = new CommunicatorWebSocketMessage();
        webSocketMessage.setOperation(CommunicatorWebSocketMessageOperation.UNSUBSCRIBEFROMPROPERTY);
        webSocketMessage.setProperty(property);
        sendMessageToServer(webSocketMessage);
    }

    @Override
    public void update(CommunicatorMessage message) {
        CommunicatorWebSocketMessage webSocketMessage = new CommunicatorWebSocketMessage();
        webSocketMessage.setOperation(CommunicatorWebSocketMessageOperation.UPDATEPROPERTY);
        webSocketMessage.setProperty(message.getProperty());
        webSocketMessage.setContent(message.getContent());
        sendMessageToServer(webSocketMessage);
    }

    private void sendMessageToServer(CommunicatorWebSocketMessage message) {
        String jsonMessage = gson.toJson(message);
        // Use asynchronous communication
        session.getAsyncRemote().sendText(jsonMessage);
    }

    /**
     * Get the latest message received from the websocket communication.
     * @return The message from the websocket communication
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message, but no action is taken when the message is changed.
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Start a WebSocket client.
     */
    private void startClient() {
        log.info("[WebSocket Client start]");
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(uri));

        } catch (IOException | URISyntaxException | DeploymentException ex) {
            // do something useful eventually
            ex.printStackTrace();
        }
    }

    /**
     * Stop the client when it is running.
     */
    private void stopClient(){
        log.info("[WebSocket Client stop]");
        try {
            session.close();

        } catch (IOException ex){
            // do something useful eventually
            ex.printStackTrace();
        }
    }

    // Process incoming json message
    private void processMessage(String jsonMessage) {

        // Parse incoming message
        CommunicatorWebSocketMessage wsMessage;
        try {
            wsMessage = gson.fromJson(jsonMessage, CommunicatorWebSocketMessage.class);
        }
        catch (JsonSyntaxException ex) {
            log.info("[WebSocket Client ERROR: cannot parse Json message {}", jsonMessage);
            return;
        }

        // Only operation update property will be further processed
        CommunicatorWebSocketMessageOperation operation;
        operation = wsMessage.getOperation();
        if (operation == null || operation != CommunicatorWebSocketMessageOperation.UPDATEPROPERTY) {
            log.info("[WebSocket Client ERROR: update property operation expected]");
            return;
        }

        // Obtain property from message
        String property = wsMessage.getProperty();
        if (property == null || "".equals(property)) {
            log.info("[WebSocket Client ERROR: property not defined]");
            return;
        }

        // Obtain content from message
        String content = wsMessage.getContent();
        if (content == null || "".equals(content)) {
            log.info("[WebSocket Client ERROR: message without content]");
            return;
        }

        // Create instance of CommunicaterMessage for observers
        CommunicatorMessage commMessage = new CommunicatorMessage();
        commMessage.setProperty(property);
        commMessage.setContent(content);

        // Notify observers
        this.setChanged();
        this.notifyObservers(commMessage);
    }
}
