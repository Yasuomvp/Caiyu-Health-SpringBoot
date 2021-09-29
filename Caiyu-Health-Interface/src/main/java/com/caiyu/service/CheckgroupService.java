package com.caiyu.service;

import com.caiyu.entity.PageResult;
import com.caiyu.entity.QueryPageBean;
import com.caiyu.pojo.CheckGroup;

import java.util.List;

public interface CheckgroupService {
    PageResult findPage(QueryPageBean queryPageBean) throws Exception;

    void add(CheckGroup checkGroup, List<Integer> checkItems) throws Exception;

    CheckGroup findById(Integer id) throws Exception;

    List<Integer> findCheckItems(Integer id) throws Exception;

    void deleteCheckitemsRelationsByGroupId(CheckGroup checkGroup) throws Exception;

    void edit(CheckGroup checkGroup,List<Integer> checkitemIds) throws Exception;

    void deleteGroupById(Integer id) throws Exception;

    List<CheckGroup> findAll() throws Exception;
}
