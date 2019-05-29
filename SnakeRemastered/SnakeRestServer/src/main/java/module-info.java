module SnakeRestServer {
    requires slf4j.api;
    requires gson;
    requires java.ws.rs;
    requires jetty.servlet;
    requires jetty.server;
    requires jersey.container.servlet.core;
    exports restModel;
    exports restServer;
}