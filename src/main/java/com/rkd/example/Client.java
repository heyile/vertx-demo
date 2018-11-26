package com.rkd.example;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.metrics.MetricsOptions;

public class Client {

  public static void main(String[] args) {

    VertxOptions vertxOptions = new VertxOptions();

    Vertx vertx = Vertx.vertx(vertxOptions);
    HttpClientOptions http2ClientOption = new HttpClientOptions();
    http2ClientOption.setProtocolVersion(HttpVersion.HTTP_2)
        .setUseAlpn(true)
        .setIdleTimeout(0)
        .setHttp2ClearTextUpgrade(false)
        .setTrustAll(true)
        .setSsl(true);

    HttpClient http2Client = vertx.createHttpClient(http2ClientOption);

    http2Client.getNow(8081, "localhost", "", resp -> {
      resp.bodyHandler(buff -> {
        System.out.println(buff.toString());
      });
    });
  }
}
