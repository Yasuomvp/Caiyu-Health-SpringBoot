package com.caiyu.caiyuhealthprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@MapperScan("com.caiyu.dao")
@SpringBootApplication(scanBasePackages = "com.caiyu.service.impl")
public class CaiyuHealthProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaiyuHealthProviderApplication.class, args);
    }

}
