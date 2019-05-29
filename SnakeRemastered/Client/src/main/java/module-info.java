module Client {
    requires java.sql;
    requires java.logging;
    requires javafx.graphics;
    requires gson;
    requires slf4j.api;
    requires javax.websocket.client.api;
    requires javafx.fxml;
    requires javafx.controls;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;

    exports Controllers;
    exports Models;
    exports CommunicatorClient;
    exports sample;
}