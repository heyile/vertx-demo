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

import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.WebSocket;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.HttpClientMetrics;

public class DefaultHttpClientMetrics implements
    HttpClientMetrics<Object, Object, Object, DefaultClientEndpointMetric, Object> {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultHttpClientMetrics.class);


  private final HttpClient client;

  private final HttpClientOptions options;

  public DefaultHttpClientMetrics(HttpClient client, HttpClientOptions options) {
    this.client = client;
    this.options = options;
  }

  public HttpClient getClient() {
    return client;
  }

  public HttpClientOptions getOptions() {
    return options;
  }

  @Override
  public DefaultClientEndpointMetric createEndpoint(String host, int port, int maxPoolSize) {
    return DefaultClientEndpointMetric.INSTANCE;
  }

  @Override
  public void closeEndpoint(String host, int port, DefaultClientEndpointMetric endpointMetric) {
  }

  @Override
  public Object enqueueRequest(DefaultClientEndpointMetric endpointMetric) {
    return null;
  }

  @Override
  public void dequeueRequest(DefaultClientEndpointMetric endpointMetric, Object taskMetric) {
  }

  @Override
  public void endpointConnected(DefaultClientEndpointMetric endpointMetric, Object socketMetric) {
    endpointMetric.setAdded(true);
  }

  @Override
  public void endpointDisconnected(DefaultClientEndpointMetric endpointMetric, Object socketMetric) {
  }

  @Override
  public Object requestBegin(DefaultClientEndpointMetric endpointMetric,
      Object socketMetric, SocketAddress localAddress, SocketAddress remoteAddress,
      HttpClientRequest request) {
    System.out.println("client request begin");
    return null;
  }

  @Override
  public void requestEnd(Object requestMetric) {
  }

  @Override
  public void responseBegin(Object requestMetric, HttpClientResponse response) {
  }

  @Override
  public Object responsePushed(DefaultClientEndpointMetric endpointMetric,
      Object socketMetric,
      SocketAddress localAddress,
      SocketAddress remoteAddress, HttpClientRequest request) {
    return null;
  }

  @Override
  public void requestReset(Object requestMetric) {
  }

  @Override
  public void responseEnd(Object requestMetric, HttpClientResponse response) {
  }

  @Override
  public Object connected(DefaultClientEndpointMetric endpointMetric, Object socketMetric,
      WebSocket webSocket) {
    return null;
  }

  @Override
  public void disconnected(Object webSocketMetric) {

  }

  @Override
  public Object connected(SocketAddress remoteAddress, String remoteName) {
    return new Object();
  }

  @Override
  public void disconnected(Object socketMetric, SocketAddress remoteAddress) {
  }

  @Override
  public void bytesRead(Object socketMetric, SocketAddress remoteAddress, long numberOfBytes) {
    System.out.println("client read : " + numberOfBytes);

  }

  @Override
  public void bytesWritten(Object socketMetric, SocketAddress remoteAddress, long numberOfBytes) {
    System.out.println("client write  : " + numberOfBytes);
  }

  @Override
  public void exceptionOccurred(Object socketMetric, SocketAddress remoteAddress, Throwable t) {

  }

  @Override
  @Deprecated
  public boolean isEnabled() {
    return true;
  }

  @Override
  public void close() {

  }
}
