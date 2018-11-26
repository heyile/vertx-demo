package com.rkd.example;

import com.rkd.example.metric.DefaultVertxMetricsFactory;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.metrics.MetricsOptions;
import io.vertx.core.net.PemKeyCertOptions;

public class Server {

  public static void main(String[] args) {
    HttpServerOptions httpServerOptions = new HttpServerOptions();
    httpServerOptions.setUseAlpn(true)
        .setSsl(true)
        .setPemKeyCertOptions(new PemKeyCertOptions().setKeyPath("tsl/server-key.pem").setCertPath("tsl/server-cert.pem"));
    VertxOptions vertxOptions = new VertxOptions();
    DefaultVertxMetricsFactory defaultVertxMetricsFactory = new DefaultVertxMetricsFactory();
    MetricsOptions metricsOptions = defaultVertxMetricsFactory.newOptions();
    vertxOptions.setMetricsOptions(metricsOptions);
    Vertx vertx = Vertx.vertx(vertxOptions);

    HttpServer httpServer = vertx.createHttpServer();

    httpServer.requestHandler(request -> {
      request.response().end("hello world");
    });

    httpServer.listen(8080);
    System.out.println("server start successful ");
  }

}
