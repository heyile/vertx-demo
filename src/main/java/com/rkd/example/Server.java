package com.rkd.example;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.net.PemKeyCertOptions;

public class Server {

  public static void main(String[] args) {
    HttpServerOptions httpServerOptions = new HttpServerOptions();
    httpServerOptions.setUseAlpn(true)
        .setSsl(true)
        .setPemKeyCertOptions(new PemKeyCertOptions().setKeyPath("tsl/server-key.pem").setCertPath("tsl/server-cert.pem"));

    Vertx vertx = Vertx.vertx();

    HttpServer httpServer = vertx.createHttpServer(httpServerOptions);

    httpServer.requestHandler(request -> {
      request.response().end("hello world");
    });

    httpServer.listen(8080);
    System.out.println("server start successful ");
  }

}
