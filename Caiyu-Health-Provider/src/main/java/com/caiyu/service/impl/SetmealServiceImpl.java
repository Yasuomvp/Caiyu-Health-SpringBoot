package com.caiyu.service.impl;

import com.caiyu.constant.RedisConstant;
import com.caiyu.dao.JustSetmealMapper;
import com.caiyu.dao.TSetmealCheckgroupMapper;
import com.caiyu.dao.TSetmealMapper;
import com.caiyu.entity.PageResult;
import com.caiyu.entity.QueryPageBean;
import com.caiyu.pojo.Setmeal;
import com.caiyu.pojo.TSetmeal;
import com.caiyu.pojo.TSetmealCheckgroupKey;
import com.caiyu.service.SetmealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.List;


@Component
@Service( interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private TSetmealMapper tSetmealMapper;

    @Autowired
    private TSetmealCheckgroupMapper tSetmealCheckgroupMapper;

    @Autowired
    private JustSetmealMapper justSetmealMapper;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public void add(TSetmeal tSetmeal, List<Integer> checkgroupIds) throws Exception {
        tSetmealMapper.insertSelective(tSetmeal);
        Integer id = tSetmeal.getId();
        for (Integer checkgroupId : checkgroupIds) {
            TSetmealCheckgroupKey key = new TSetmealCheckgroupKey();
            key.setSetmealId(id);
            key.setCheckgroupId(checkgroupId);
            tSetmealCheckgroupMapper.insertSelective(key);
        }
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,tSetmeal.getImg());
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) throws Exception {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page = justSetmealMapper.selectByQuerryString(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List<Setmeal> findAll() throws Exception {
        return justSetmealMapper.findAll();
    }

    @Override
    public Setmeal findById(int id) throws Exception {
        return justSetmealMapper.findById(id);
    }
}
