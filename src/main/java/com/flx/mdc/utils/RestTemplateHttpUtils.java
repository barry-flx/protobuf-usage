package com.flx.mdc.utils;

import com.flx.mdc.config.ClientHttpMdcInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class RestTemplateHttpUtils {
    private static RestTemplate restTemplate = new RestTemplate();

    public static String doGet(String url) {
        restTemplate.setInterceptors(Collections.singletonList(new ClientHttpMdcInterceptor()));
        return restTemplate.getForObject(url, String.class);
    }
}
