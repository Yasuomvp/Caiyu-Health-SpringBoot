package com.caiyu.controller;


import com.caiyu.service.TestService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Reference
    private TestService testService;

    @RequestMapping("/hello")
    public String hello(){
        return testService.sayHello();
    }
}
