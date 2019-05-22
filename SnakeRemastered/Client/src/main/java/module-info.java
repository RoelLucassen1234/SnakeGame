module Client{
    requires java.sql;
    requires java.logging;
    requires javafx.graphics;
    requires gson;
    requires slf4j.api;
    requires javax.websocket.client.api;


    exports Models;
exports CommunicatorClient;
    exports sample;
}