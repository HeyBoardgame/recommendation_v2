package com.yeoboge.recommendation.global.util;

import org.springframework.web.client.RestClient;

public class RestClientUtil {
    public static RestClient createClient(String baseUrl) {
        return RestClient.create(baseUrl);
    }
}
