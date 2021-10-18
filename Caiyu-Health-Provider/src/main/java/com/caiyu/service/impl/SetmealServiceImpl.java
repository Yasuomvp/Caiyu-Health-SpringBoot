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
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import redis.clients.jedis.JedisPool;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${out_put_path}")
    private String outputpath;

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

        if (tSetmeal.getImg()!=null)
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,tSetmeal.getImg());


        generateMobileStaticHtml();
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

    @Override
    public List<Map<String, Object>> findSetmealCount() {
        return justSetmealMapper.findSetmealCount();
    }

    public void generateMobileStaticHtml() throws Exception {
        //准备模板文件中所需的数据
        List<Setmeal> setmealList = this.findAll();

        System.out.println(setmealList);
        //生成套餐列表静态页面
        generateMobileSetmealListHtml(setmealList);
        //生成套餐详情静态页面（多个）
        generateMobileSetmealDetailHtml(setmealList);
    }

    public void generateMobileSetmealListHtml(List<Setmeal> setmealList){
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("setmealList",setmealList);
        this.generateHtml("mobile_setmeal.ftl","m_setmeal.html",dataMap);
    }

    public void generateMobileSetmealDetailHtml(List<Setmeal> setmealList) throws Exception {
        for (Setmeal setmeal : setmealList) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("setmeal",this.findById(setmeal.getId()));
            this.generateHtml("mobile_setmeal_detail.ftl","setmeal_detail_"+setmeal.getId()+".html", dataMap);
        }
    }

    public void generateHtml(String templateName, String htmlPageName, Map<String,Object> dataMap){
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Writer out = null;
        try{
            Template template = configuration.getTemplate(templateName);
            File docFile = new File(outputpath+"\\"+htmlPageName);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            template.process(dataMap,out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(out!=null){
                try {
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
