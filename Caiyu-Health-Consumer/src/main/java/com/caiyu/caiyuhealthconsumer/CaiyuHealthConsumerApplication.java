package com.caiyu.caiyuhealthconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@SpringBootApplication(scanBasePackages = "com.caiyu.controller")
@ImportResource(locations = {"classpath:spring-redis.xml"})
public class CaiyuHealthConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaiyuHealthConsumerApplication.class, args);
    }

    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        return multipartResolver;
    }

}
