package com.rkd.example.metric;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.HttpServerMetrics;

public class DefaultHttpServerMetrics implements HttpServerMetrics<Void,Void,Void> {
  @Override
  public Void requestBegin(Void socketMetric, HttpServerRequest request) {
    System.out.println("server request begin");
    return null;
  }

  @Override
  public void requestReset(Void requestMetric) {
    System.out.println("server request reset ");
  }

  @Override
  public Void responsePushed(Void socketMetric, HttpMethod method, String uri, HttpServerResponse response) {
    return null;
  }

  @Override
  public void responseEnd(Void requestMetric, HttpServerResponse response) {

  }

  @Override
  public Void upgrade(Void requestMetric, ServerWebSocket serverWebSocket) {
    return null;
  }

  @Override
  public Void connected(Void socketMetric, ServerWebSocket serverWebSocket) {
    System.out.println("server connected");
    return null;
  }

  @Override
  public void disconnected(Void serverWebSocketMetric) {
    System.out.println("server disConnected");

  }

  @Override
  public Void connected(SocketAddress remoteAddress, String remoteName) {
    System.out.println("server connected");

    return null;
  }

  @Override
  public void disconnected(Void socketMetric, SocketAddress remoteAddress) {
    System.out.println("server disConnected");

  }

  @Override
  public void bytesRead(Void socketMetric, SocketAddress remoteAddress, long numberOfBytes) {
    System.out.println("server bytesRead : " + numberOfBytes);
  }

  @Override
  public void bytesWritten(Void socketMetric, SocketAddress remoteAddress, long numberOfBytes) {
    System.out.println("server bytesWritten : " + numberOfBytes);

  }

  @Override
  public void exceptionOccurred(Void socketMetric, SocketAddress remoteAddress, Throwable t) {

  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public void close() {

  }
}
