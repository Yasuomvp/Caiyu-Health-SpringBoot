package com.caiyu.caiyuhealthconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.caiyu.controller")
public class CaiyuHealthConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaiyuHealthConsumerApplication.class, args);
    }

}
