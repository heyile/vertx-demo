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
//    DefaultVertxMetricsFactory defaultVertxMetricsFactory = new DefaultVertxMetricsFactory();
//    MetricsOptions metricsOptions = defaultVertxMetricsFactory.newOptions();
//    vertxOptions.setMetricsOptions(defaultVertxMetricsFactory.newOptions());
    Vertx vertx = Vertx.vertx(vertxOptions);
    HttpClientOptions http2ClientOption = new HttpClientOptions();
    http2ClientOption.setProtocolVersion(HttpVersion.HTTP_2)
        .setUseAlpn(true)
        .setIdleTimeout(0)
        .setHttp2ClearTextUpgrade(false)
        .setTrustAll(true)
        .setSsl(true);

    HttpClient http2Client = vertx.createHttpClient(http2ClientOption);

    HttpClient localhost = http2Client.getNow(8080, "localhost", "", resp -> {
      resp.bodyHandler(buff -> {
        System.out.println(buff.toString());
      });
    });
  }
}
