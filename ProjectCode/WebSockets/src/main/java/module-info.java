module WebSockets {
    requires slf4j.api;
    requires jetty.server;
    requires jetty.servlet;
    requires javax.websocket.api;
    requires javax.websocket.server.impl;
    requires javax.websocket.client.api;
    requires gson;
    requires Client;
}