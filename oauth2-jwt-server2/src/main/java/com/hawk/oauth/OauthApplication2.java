package com.hawk.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Title: OauthApplication2
 * @ProjectName springboot-oauth2-jwt
 * @Author Hawk
 * @Date 2021/1/19 14:42
 */
@SpringBootApplication(scanBasePackages = {"com.hawk"})
public class OauthApplication2 {
    public static void main(String[] args) {
        SpringApplication.run(OauthApplication2.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
}
