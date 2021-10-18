package com.caiyu.service;

import com.caiyu.entity.PageResult;
import com.caiyu.entity.QueryPageBean;
import com.caiyu.pojo.Setmeal;
import com.caiyu.pojo.TSetmeal;

import java.util.List;
import java.util.Map;

public interface SetmealService {
    void add(TSetmeal tSetmeal, List<Integer> checkgroupIds) throws Exception;

    PageResult findPage(QueryPageBean queryPageBean) throws Exception;

    public List<Setmeal> findAll() throws Exception;

    public Setmeal findById(int id) throws Exception;

    public List<Map<String,Object>> findSetmealCount();

//    public void generateMobileStaticHtml() throws Exception;
//
//    public void generateMobileSetmealListHtml(List<Setmeal> setmealList) throws Exception;
//
//    public void generateMobileSetmealDetailHtml(List<Setmeal> setmealList) throws Exception;
//
//    public void generateHtml(String templateName, String htmlPageName, Map<String,Object> dataMap) throws Exception;
}
