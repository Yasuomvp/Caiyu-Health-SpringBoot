package com.caiyu.service.impl;

import com.caiyu.service.TestService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;


@Component
@Service
public class TestServiceImpl implements TestService {
    @Override
    public String sayHello() {
        return "Hello";
    }
}
