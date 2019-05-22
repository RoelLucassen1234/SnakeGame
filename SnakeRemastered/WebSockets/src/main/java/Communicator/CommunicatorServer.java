/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communicator;

import javax.websocket.server.ServerContainer;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Nico Kuijpers
 */
public class CommunicatorServer {
    private static final Logger log = LoggerFactory.getLogger(CommunicatorServer.class);

    private static final int PORT = 8097;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        startWebSocketServer();
    }

    // Start the web socket server
    private static void startWebSocketServer() {

        Server webSocketServer = new Server();
        ServerConnector connector = new ServerConnector(webSocketServer);
        connector.setPort(PORT);
        webSocketServer.addConnector(connector);

        // Setup the basic application "context" for this application at "/"
        // This is also known as the handler tree (in jetty speak)
        ServletContextHandler webSocketContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        webSocketContext.setContextPath("/");
        webSocketServer.setHandler(webSocketContext);

        try {
            // Initialize javax.websocket layer
            ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(webSocketContext);

            // Add WebSocket endpoint to javax.websocket layer
            wscontainer.addEndpoint(CommunicatorServerWebSocket.class);

            webSocketServer.start();

            webSocketServer.join();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
