package com.rkd.example;

import com.rkd.example.metric.DefaultVertxMetricsFactory;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;

public class Server {

  public static void main(String[] args) {
    HttpServerOptions httpServerOptions = new HttpServerOptions();
    VertxOptions vertxOptions = new VertxOptions();
    DefaultVertxMetricsFactory defaultVertxMetricsFactory = new DefaultVertxMetricsFactory();
    vertxOptions.setMetricsOptions(defaultVertxMetricsFactory.newOptions());
    Vertx vertx = Vertx.vertx(vertxOptions);

    HttpServer httpServer = vertx.createHttpServer(httpServerOptions);

    httpServer.requestHandler(request -> {
      request.bodyHandler(body -> {
        System.out.println("server receive : " + body.toString());
      });
      request.response().end("hello world");
    });

    httpServer.listen(8081);
    System.out.println("server start successful ");
  }

}
