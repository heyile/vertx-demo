package com.rkd.example.metric;

import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.metrics.impl.DummyVertxMetrics;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.HttpClientMetrics;
import io.vertx.core.spi.metrics.HttpServerMetrics;

public class DefaultVertxMetrics extends DummyVertxMetrics {


  private VertxOptions vertxOptions;


  public DefaultVertxMetrics(VertxOptions vertxOptions) {
     this.vertxOptions = vertxOptions;
  }


  @Override
  public HttpServerMetrics<?, ?, ?> createHttpServerMetrics(HttpServerOptions options, SocketAddress localAddress) {
    return null;
  }

  @Override
  public HttpClientMetrics<?, ?, ?, ?, ?> createHttpClientMetrics(HttpClientOptions options) {
    return  new DefaultHttpClientMetrics(options);
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
