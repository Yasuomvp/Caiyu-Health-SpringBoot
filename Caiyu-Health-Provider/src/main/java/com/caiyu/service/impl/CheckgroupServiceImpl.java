package com.caiyu.service.impl;


import com.caiyu.dao.CheckgroupMapper;
import com.caiyu.entity.PageResult;
import com.caiyu.entity.QueryPageBean;
import com.caiyu.pojo.CheckGroup;
import com.caiyu.pojo.CheckItem;
import com.caiyu.service.CheckgroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Service(interfaceClass = CheckgroupService.class)
@Transactional
public class CheckgroupServiceImpl implements CheckgroupService {

    @Autowired
    private CheckgroupMapper checkgroupMapper;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) throws Exception {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckGroup> checkGoups = checkgroupMapper.findPage(queryString);
        return new PageResult(checkGoups.getTotal(), checkGoups.getResult());
    }

    @Override
    public void add(CheckGroup checkGroup, List<Integer> checkItems) throws Exception {
        checkgroupMapper.add(checkGroup);
        Integer checkgroupid = checkGroup.getId();
        for (Integer checkitemid : checkItems) {
            checkgroupMapper.addgroup(checkgroupid,checkitemid);
        }
    }

    @Override
    public CheckGroup findById(Integer id) throws Exception {
        return checkgroupMapper.findById(id);
    }

    @Override
    public List<Integer> findCheckItems(Integer id) throws Exception {
        return checkgroupMapper.findCheckItems(id);
    }

    @Override
    public void deleteCheckitemsRelationsByGroupId(CheckGroup checkGroup) throws Exception {
        checkgroupMapper.deleteCheckitemsRelationsByGroupId(checkGroup.getId());
    }

    @Override
    public void edit(CheckGroup checkGroup, List<Integer> checkitemIds) throws Exception {
        Integer checkgroupid = checkGroup.getId();
        checkgroupMapper.edit(checkGroup);
        checkgroupMapper.deleteCheckitemsRelationsByGroupId(checkgroupid);
        for (Integer checkitemid : checkitemIds) {
            checkgroupMapper.addgroup(checkgroupid,checkitemid);
        }
    }

    @Override
    public void deleteGroupById(Integer id) throws Exception {

        checkgroupMapper.deleteCheckitemsRelationsByGroupId(id);
        checkgroupMapper.deleteGroupById(id);

    }


}
