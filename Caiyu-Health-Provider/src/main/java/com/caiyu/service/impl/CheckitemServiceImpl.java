package com.caiyu.service.impl;

import com.caiyu.dao.CheckitemMapper;
import com.caiyu.entity.PageResult;
import com.caiyu.entity.QueryPageBean;
import com.caiyu.pojo.CheckItem;
import com.caiyu.service.CheckitemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service
public class CheckitemServiceImpl implements CheckitemService {

    @Autowired
    private CheckitemMapper checkitemMapper;

    @Override
    public void add(CheckItem checkItem) throws Exception {
        checkitemMapper.add(checkItem);
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) throws Exception {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckItem> checkItems = checkitemMapper.selectByItem(queryString);
        return new PageResult(checkItems.getTotal(), checkItems.getResult());
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        Long countCheckitemId = checkitemMapper.findCountByCheckitemId(id);
        if (countCheckitemId > 0){
            new RuntimeException();
        }
        checkitemMapper.deleteById(id);
    }

    @Override
    public void edit(CheckItem checkItem) throws Exception {
        checkitemMapper.edit(checkItem);
    }

    @Override
    public CheckItem findById(Integer id) throws Exception {
        return checkitemMapper.findById(id);
    }

    @Override
    public List<CheckItem> findAll() throws Exception {
        return checkitemMapper.findAll();
    }


}
