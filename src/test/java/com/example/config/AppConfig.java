package com.example.config;

import io.gatling.javaapi.http.HttpProtocolBuilder;
import static io.gatling.javaapi.http.HttpDsl.http;

public class AppConfig {
    public static final HttpProtocolBuilder HTTP_PROTOCOL = http
            .baseUrl("http://webtours.load-test.ru:1080")
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .userAgentHeader("Gatling/LoadTest");
}