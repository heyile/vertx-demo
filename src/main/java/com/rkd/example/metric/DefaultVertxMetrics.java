package com.rkd.example.metric;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.metrics.impl.DummyVertxMetrics;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.HttpClientMetrics;
import io.vertx.core.spi.metrics.HttpServerMetrics;

public class DefaultVertxMetrics extends DummyVertxMetrics {
  private final Vertx vertx;

  private VertxOptions vertxOptions;


  public DefaultVertxMetrics(Vertx vertx, VertxOptions vertxOptions) {
    this.vertx = vertx;
    this.vertxOptions = vertxOptions;
  }

  public Vertx getVertx() {
    return vertx;
  }


  @Override
  public HttpServerMetrics createMetrics(HttpServer server, SocketAddress localAddress, HttpServerOptions options) {
    return new DefaultHttpServerMetrics();
  }
  @Override
  public HttpClientMetrics<?, ?, ?, ?, ?> createMetrics(HttpClient client, HttpClientOptions options) {

    return new DefaultHttpClientMetrics(client, options);
  }

  @Override
  public boolean isMetricsEnabled() {
    return true;
  }

  @Deprecated
  @Override
  public boolean isEnabled() {
    return true;
  }
}
