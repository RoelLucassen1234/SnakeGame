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

    exports controllers;
    exports logica;
    exports enums;
    exports Interface;
    exports models;
    exports communicatorclient;
    exports sample;

    opens controllers;
    opens models;
}