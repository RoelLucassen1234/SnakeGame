module SnakeRestServer {
    requires slf4j.api;
    requires gson;
    requires jetty.server;
    requires jetty.servlet;
    requires jersey.container.servlet.core;
    requires java.ws.rs;
    requires java.xml.bind;
    requires java.sql;
    requires Client;

    opens restServer;
    opens controller;
}