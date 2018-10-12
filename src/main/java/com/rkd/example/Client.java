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

    System.out.println("before http1.1 : DefaultClientEndpointMetric.INSTANCE.getAdded() is " + DefaultClientEndpointMetric.INSTANCE.getAdded());
    HttpClientOptions httpClientOptions = new HttpClientOptions();
    httpClientOptions.setKeepAlive(true);
    httpClientOptions.setProtocolVersion(HttpVersion.HTTP_1_1);
    HttpClient httpClient = vertx.createHttpClient(httpClientOptions);
    httpClient.getNow(8080, "localhost", "", resp -> {
      resp.bodyHandler(buff -> {
        System.out.println(buff.toString());
        countDownLatch.countDown();
      });
    });

    try {
      countDownLatch.await();
      System.out.println("after http1.1:  DefaultClientEndpointMetric.INSTANCE.getAdded() is " + DefaultClientEndpointMetric.INSTANCE.getAdded());
      DefaultClientEndpointMetric.INSTANCE.setAdded(false);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("before http2:  DefaultClientEndpointMetric.INSTANCE.getAdded() is " + DefaultClientEndpointMetric.INSTANCE.getAdded());

    HttpClientOptions http2ClientOption = new HttpClientOptions();
    http2ClientOption.setProtocolVersion(HttpVersion.HTTP_2)
        .setUseAlpn(true)
        .setHttp2ClearTextUpgrade(false)
        .setSsl(false);

    HttpClient http2Client = vertx.createHttpClient(http2ClientOption);

    http2Client.getNow(8080, "localhost", "", resp -> {
      resp.bodyHandler(buff -> {
        System.out.println(buff.toString());
        System.out.println("after http2:  DefaultClientEndpointMetric.INSTANCE.getAdded() is " + DefaultClientEndpointMetric.INSTANCE.getAdded());
      });
    });
  }
}
