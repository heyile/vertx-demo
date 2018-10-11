package com.rkd.example.metric;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.metrics.MetricsOptions;
import io.vertx.core.spi.VertxMetricsFactory;
import io.vertx.core.spi.metrics.VertxMetrics;

public class DefaultVertxMetricsFactory implements VertxMetricsFactory {
  private DefaultVertxMetrics vertxMetrics;

  public DefaultVertxMetrics getVertxMetrics() {
    return vertxMetrics;
  }

  @Override
  public synchronized VertxMetrics metrics(Vertx vertx, VertxOptions options) {
    if (vertxMetrics == null) {
      vertxMetrics = new DefaultVertxMetrics(vertx, options);
    }
    return vertxMetrics;
  }

  @Override
  public MetricsOptions newOptions() {
    MetricsOptions metricsOptions = new MetricsOptions();
    metricsOptions.setFactory(this);
    metricsOptions.setEnabled(true);
    return metricsOptions;
  }
}
