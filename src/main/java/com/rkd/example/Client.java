package com.rkd.example;

import java.util.concurrent.CountDownLatch;

import com.rkd.example.metric.DefaultClientEndpointMetric;
import com.rkd.example.metric.DefaultVertxMetricsFactory;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpVersion;

public class Client {

  public static void main(String[] args) {
    CountDownLatch countDownLatch = new CountDownLatch(1);

    VertxOptions vertxOptions = new VertxOptions();
    DefaultVertxMetricsFactory defaultVertxMetricsFactory = new DefaultVertxMetricsFactory();
    vertxOptions.setMetricsOptions(defaultVertxMetricsFactory.newOptions());
    Vertx vertx = Vertx.vertx(vertxOptions);

    System.out.println("before http2:  DefaultClientEndpointMetric.INSTANCE.getAdded() is " + DefaultClientEndpointMetric.INSTANCE.getAdded());
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
        System.out.println(
            "after http2:  DefaultClientEndpointMetric.INSTANCE.getAdded() is " + DefaultClientEndpointMetric.INSTANCE
                .getAdded());
      });
    });
  }
}
