package com.caiyu.caiyuhealthjobs;

import com.caiyu.jobs.ClearImgJob;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;

@SpringBootApplication
@ImportResource(locations = {"classpath:spring-jobs.xml"})
public class CaiyuHealthJobsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaiyuHealthJobsApplication.class, args);
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
}
