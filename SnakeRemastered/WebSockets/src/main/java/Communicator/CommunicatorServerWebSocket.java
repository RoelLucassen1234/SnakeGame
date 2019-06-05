package Communicator;

import Models.CommunicatorWebSocketMessage;
import Models.CommunicatorWebSocketMessageOperation;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.*;

// https://github.com/jetty-project/embedded-jetty-websocket-examples/tree/master/javax.websocket-example/src/main/java/org/eclipse/jetty/demo

/**
 * Server-side implementation of Communicator using WebSockets for communication.
 *
 * This code is based on example code from:
 * https://github.com/jetty-project/embedded-jetty-websocket-examples/blob/
 * master/javax.websocket-example/src/main/java/org/eclipse/jetty/
 * demo/EventServerSocket.java
 *
 * @author Nico Kuijpers, based on example project
 */

@ServerEndpoint(value="/communicator/")
public class CommunicatorServerWebSocket {
    private static final Logger log = LoggerFactory.getLogger(CommunicatorServerWebSocket.class);

    // All sessions
    private static final List<Session> sessions = new ArrayList<>();

    // Map each property to list of sessions that are subscribed to that property
    private static final Map<String,List<Session>> propertySessions = new HashMap<>();

    @OnOpen
    public void onConnect(Session session) {
        log.info("[WebSocket Connected] SessionID: {}", session.getId());
        log.info("[New client with client side session ID]: {}", session.getId());
        sessions.add(session);
        log.info("[#sessions]: {}", sessions.size());
    }

    @OnMessage
    public void onText(String message, Session session) {
        log.info("[WebSocket Session ID] : {} [Received] : {}", session.getId(), message);
        handleMessageFromClient(message, session);
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        log.info("[WebSocket Session ID] : {} [Socket Closed]: {}", session.getId(), reason);
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        log.error("[WebSocket Session ID] : {} [ERROR]: {}", session.getId(), cause.getMessage());
    }

    // Handle incoming message from client
    private void handleMessageFromClient(String jsonMessage, Session session) {
        Gson gson = new Gson();
        CommunicatorWebSocketMessage wbMessage = null;
        try {
            wbMessage = gson.fromJson(jsonMessage,CommunicatorWebSocketMessage.class);
        }
        catch (JsonSyntaxException ex) {
            log.error("[WebSocket ERROR] : cannot parse Json message {}", jsonMessage);
            return;
        }

        // Operation defined in message
        CommunicatorWebSocketMessageOperation operation;
        operation = wbMessage.getOperation();

        // Process message based on operation
        String property = wbMessage.getProperty();
        if (null != operation && null != property && !"".equals(property)) {
            switch (operation) {
                case REGISTERPROPERTY:
                    // Register property if not registered yet
                    propertySessions.computeIfAbsent(property, k -> new ArrayList<Session>());
                    break;
                case UNREGISTERPROPERTY:
                    // Do nothing as property may also have been registered by
                    // another client
                    break;
                case SUBSCRIBETOPROPERTY:
                    // Subsribe to property if the property has been registered
                    if (propertySessions.get(property) != null) {
                        propertySessions.get(property).add(session);
                    }
                    break;
                case UNSUBSCRIBEFROMPROPERTY:
                    // Unsubsribe from property if the property has been registered
                    if (propertySessions.get(property) != null) {
                        propertySessions.get(property).remove(session);
                    }
                    break;
                case UPDATEPROPERTY:
                    // Send the message to all clients that are subscribed to this property
                    if (propertySessions.get(property) != null) {
                        log.info("[WebSocket send] {} to:", jsonMessage);
                        if(property.equals("CheckSeed")){
                            if(propertySessions.get(property).size() == 1){
                                CommunicatorData.setSeed(new Random().nextInt(1000));
                            }
                            // Return seed
                            log.info("\t\t >> Client associated with server side session ID: {}", session.getId());
                            CommunicatorWebSocketMessage message = new CommunicatorWebSocketMessage();
                            message.setProperty("CheckSeed");
                            message.setContent(Long.toString(CommunicatorData.getSeed()));
                            message.setOperation(CommunicatorWebSocketMessageOperation.UPDATEPROPERTY);
                            session.getAsyncRemote().sendText(gson.toJson(message));
                        }
                        else {
                            for (Session sess : propertySessions.get(property)) {
                                // Use asynchronous communication
                                log.info("\t\t >> Client associated with server side session ID: {}", sess.getId());
                                sess.getAsyncRemote().sendText(jsonMessage);
                            }
                            log.info("[WebSocket end sending message to subscribers]");
                        }
                    }
                    break;
                default:
                    log.info("[WebSocket ERROR: cannot process Json message {}", jsonMessage);
                    break;
            }
        }
    }
}