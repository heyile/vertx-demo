package com.rkd.example;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;

public class Server {

  public static void main(String[] args) {
    HttpServerOptions httpServerOptions = new HttpServerOptions();

    Vertx vertx = Vertx.vertx();

    HttpServer httpServer = vertx.createHttpServer(httpServerOptions);

    httpServer.requestHandler(request -> {
      request.response().end("hello world");
    });

    httpServer.listen(8080);
    System.out.println("server start successful ");
  }

}
