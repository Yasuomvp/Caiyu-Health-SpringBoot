package com.caiyu.service;

import com.caiyu.entity.Result;

import java.util.Map;

public interface OrderService {

    public Result order(Map map) throws Exception;

    public Map findById(Integer id) throws Exception;
}
