/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rkd.example.metric;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.metrics.impl.DummyVertxMetrics;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.HttpClientMetrics;
import io.vertx.core.spi.metrics.HttpServerMetrics;
import io.vertx.core.spi.metrics.TCPMetrics;

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
  public HttpServerMetrics<?, ?, ?> createMetrics(HttpServer server, SocketAddress localAddress,
      HttpServerOptions options) {
    return new DefaultHttpServerMetrics();
  }

  @Override
  public HttpClientMetrics<?, ?, ?, ?, ?> createMetrics(HttpClient client, HttpClientOptions options) {
    return new DefaultHttpClientMetrics();
  }

  @Override
  public TCPMetrics<?> createMetrics(SocketAddress localAddress, NetServerOptions options) {
    return null;
  }

  @Override
  public TCPMetrics<?> createMetrics(NetClientOptions options) {
    return null;
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
