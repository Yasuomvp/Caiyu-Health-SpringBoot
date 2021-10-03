package com.caiyu.service;

import com.caiyu.entity.PageResult;
import com.caiyu.entity.QueryPageBean;
import com.caiyu.pojo.Setmeal;
import com.caiyu.pojo.TSetmeal;

import java.util.List;

public interface SetmealService {
    void add(TSetmeal tSetmeal, List<Integer> checkgroupIds) throws Exception;

    PageResult findPage(QueryPageBean queryPageBean) throws Exception;

    public List<Setmeal> findAll() throws Exception;

    public Setmeal findById(int id) throws Exception;
}
