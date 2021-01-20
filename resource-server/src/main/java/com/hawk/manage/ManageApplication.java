package com.hawk.manage;

import com.hawk.manage.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Title: Application
 * @ProjectName springcloud-oauth2
 * @Author Hawk
 * @Date 2021/1/2 11:35
 */
@SpringBootApplication
public class ManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

//    @Bean
//    public FilterRegistrationBean<TokenFilter> registerLoginCheckFilter(TokenFilter tokenFilter) {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(tokenFilter);
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setName("tokenFilter");
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }

}
