package com.caiyu.service;

import com.caiyu.entity.PageResult;
import com.caiyu.entity.QueryPageBean;
import com.caiyu.pojo.CheckItem;

public interface CheckitemService {

    void add(CheckItem checkItem) throws Exception;

    PageResult pageQuery(QueryPageBean queryPageBean) throws Exception;

    void deleteById(Integer id) throws Exception;

    void edit(CheckItem checkItem) throws Exception;

    CheckItem findById(Integer id) throws Exception;
}
