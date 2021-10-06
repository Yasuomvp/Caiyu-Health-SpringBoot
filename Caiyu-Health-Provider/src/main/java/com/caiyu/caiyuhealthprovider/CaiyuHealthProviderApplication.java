package com.caiyu.caiyuhealthprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@MapperScan("com.caiyu.dao")
@SpringBootApplication(scanBasePackages = "com.caiyu.service.impl")
public class CaiyuHealthProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaiyuHealthProviderApplication.class, args);
    }

    @Bean("jedisPoolConfig")
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(200);
        jedisPoolConfig.setMaxIdle(50);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        return jedisPoolConfig;
    }

    @Bean("jedisPool")
    public JedisPool jedisPool(){
        JedisPool jedisPool = new JedisPool(jedisPoolConfig(),"127.0.0.1",6379,30000);
        return jedisPool;
    }

    @Bean("freemarkerConfig")
    public FreeMarkerConfigurer freeMarkerConfigurer(){
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:ftl/");
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
        return freeMarkerConfigurer;
    }

}
