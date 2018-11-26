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

import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.WebSocket;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.HttpClientMetrics;

/**
 * important: not singleton, every HttpClient instance relate to a HttpClientMetrics instance
 */
public class DefaultHttpClientMetrics implements
    HttpClientMetrics<DefaultHttpSocketMetric, Object, DefaultHttpSocketMetric, Object, Object> {
  public DefaultHttpClientMetrics() {
  }

  @Override
  public Object createEndpoint(String host, int port, int maxPoolSize) {
    return null;
  }

  @Override
  public void closeEndpoint(String host, int port, Object endpointMetric) {
  }

  @Override
  public Object enqueueRequest(Object endpointMetric) {
    return null;
  }

  @Override
  public void dequeueRequest(Object endpointMetric, Object taskMetric) {
  }

  @Override
  public void endpointConnected(Object endpointMetric, DefaultHttpSocketMetric socketMetric) {
  }

  @Override
  public void endpointDisconnected(Object endpointMetric, DefaultHttpSocketMetric socketMetric) {
  }

  @Override
  public DefaultHttpSocketMetric requestBegin(Object endpointMetric,
      DefaultHttpSocketMetric socketMetric, SocketAddress localAddress, SocketAddress remoteAddress,
      HttpClientRequest request) {
    return socketMetric;
  }

  @Override
  public void requestEnd(DefaultHttpSocketMetric requestMetric) {
  }

  @Override
  public void responseBegin(DefaultHttpSocketMetric requestMetric, HttpClientResponse response) {
  }

  @Override
  public DefaultHttpSocketMetric responsePushed(Object endpointMetric,
      DefaultHttpSocketMetric socketMetric,
      SocketAddress localAddress,
      SocketAddress remoteAddress, HttpClientRequest request) {
    return null;
  }

  @Override
  public void requestReset(DefaultHttpSocketMetric requestMetric) {
  }

  @Override
  public void responseEnd(DefaultHttpSocketMetric requestMetric, HttpClientResponse response) {
  }

  @Override
  public Object connected(Object endpointMetric, DefaultHttpSocketMetric socketMetric,
      WebSocket webSocket) {
    return null;
  }

  @Override
  public void disconnected(Object webSocketMetric) {

  }

  @Override
  public DefaultHttpSocketMetric connected(SocketAddress remoteAddress, String remoteName) {
    System.out.println("client connected : ");
    return null;
  }

  @Override
  public void disconnected(DefaultHttpSocketMetric socketMetric, SocketAddress remoteAddress) {
    System.out.println("client disconnected");
  }

  @Override
  public void bytesRead(DefaultHttpSocketMetric socketMetric, SocketAddress remoteAddress, long numberOfBytes) {
    System.out.println("bytes read : " + numberOfBytes);

  }

  @Override
  public void bytesWritten(DefaultHttpSocketMetric socketMetric, SocketAddress remoteAddress, long numberOfBytes) {
    System.out.println("bytes write : " + numberOfBytes);
  }

  @Override
  public void exceptionOccurred(DefaultHttpSocketMetric socketMetric, SocketAddress remoteAddress, Throwable t) {

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
