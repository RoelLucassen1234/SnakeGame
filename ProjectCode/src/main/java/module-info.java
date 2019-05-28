module ProjectCode {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
requires gson;
requires javax.websocket.api;
requires java.logging;
requires slf4j.api;
    requires javax.websocket.client.api;

    opens sample;
    opens Model;
    exports Model;

}