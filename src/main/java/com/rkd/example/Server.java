package com.rkd.example;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.metrics.MetricsOptions;
import io.vertx.core.metrics.impl.DummyVertxMetrics;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.VertxMetricsFactory;
import io.vertx.core.spi.metrics.HttpServerMetrics;

public class Server {

  public static void main(String[] args) {
    HttpServerOptions httpServerOptions = new HttpServerOptions();
    httpServerOptions.setUseAlpn(true)
        .setSsl(true)
        .setPemKeyCertOptions(
            new PemKeyCertOptions().setKeyPath("tsl/server-key.pem").setCertPath("tsl/server-cert.pem"));
    VertxOptions vertxOptions = new VertxOptions();
    VertxMetricsFactory factory = (options) -> new DummyVertxMetrics() {
      @Override
      public HttpServerMetrics<?, ?, ?> createHttpServerMetrics(HttpServerOptions options, SocketAddress localAddress) {
        return new DummyHttpServerMetrics() {
          @Override
          public Void requestBegin(Void socketMetric, HttpServerRequest request) {
            System.out.println("request begin");
            return null;
          }

          @Override
          public void responseEnd(Void requestMetric, HttpServerResponse response) {
            System.out.println("response end ");
          }

          @Override
          public Void connected(SocketAddress remoteAddress, String remoteName) {
            System.out.println("connected");
            return null;
          }

          @Override
          public void disconnected(Void socketMetric, SocketAddress remoteAddress) {
            System.out.println("disconnected");
          }

          @Override
          public void bytesRead(Void socketMetric, SocketAddress remoteAddress, long numberOfBytes) {
            System.out.println("bytes read : " + numberOfBytes);
          }

          @Override
          public void bytesWritten(Void socketMetric, SocketAddress remoteAddress, long numberOfBytes) {
            System.out.println("bytes write : " + numberOfBytes);
          }

          @Override
          public boolean isEnabled() {
            return true;
          }

          @Override
          public void close() {

          }
        };
      }
    };
    vertxOptions.setMetricsOptions(new MetricsOptions().setEnabled(true).setFactory(factory));

    Vertx vertx = Vertx.vertx(vertxOptions);
    HttpServer server = vertx.createHttpServer(httpServerOptions).requestHandler(req -> {
      HttpServerResponse response = req.response();
      response.setStatusCode(200).setChunked(true).write("bye").end();
      response.close();
    });
    server.listen(8081, "localhost", handler -> {
      if (handler.succeeded()) {
        System.out.println(" bind success");
      }
    });
  }
}
