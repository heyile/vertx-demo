package com.rkd.example.metric;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.HttpServerMetrics;

public class DefaultHttpServerMetrics implements HttpServerMetrics {
  @Override
  public Object requestBegin(Object socketMetric, HttpServerRequest request) {
    return null;
  }

  @Override
  public void requestReset(Object requestMetric) {

  }

  @Override
  public Object upgrade(Object requestMetric, ServerWebSocket serverWebSocket) {
    System.out.println("server connected");
    return null;
  }

  @Override
  public Object connected(Object socketMetric, ServerWebSocket serverWebSocket) {
    System.out.println("server connected");
    return null;
  }

  @Override
  public Object responsePushed(Object socketMetric, HttpMethod method, String uri, HttpServerResponse response) {
    return null;
  }

  @Override
  public void responseEnd(Object requestMetric, HttpServerResponse response) {

  }

  @Override
  public void disconnected(Object serverWebSocketMetric) {
    System.out.println("server disconnected");
  }

  @Override
  public Object connected(SocketAddress remoteAddress, String remoteName) {
    System.out.println("server connected");

    return null;
  }

  @Override
  public void disconnected(Object socketMetric, SocketAddress remoteAddress) {
    System.out.println("server disconnected");
  }

  @Override
  public void bytesRead(Object socketMetric, SocketAddress remoteAddress, long numberOfBytes) {
    System.out.println("server read : " + numberOfBytes);
  }

  @Override
  public void bytesWritten(Object socketMetric, SocketAddress remoteAddress, long numberOfBytes) {
    System.out.println("server write : " + numberOfBytes);
  }

  @Override
  public void exceptionOccurred(Object socketMetric, SocketAddress remoteAddress, Throwable t) {

  }

  @Override
  public boolean isEnabled() {
    return false;
  }

  @Override
  public void close() {

  }
}
