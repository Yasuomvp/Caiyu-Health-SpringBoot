package com.caiyu.controller;


import com.caiyu.constant.MessageConstant;
import com.caiyu.constant.RedisConstant;
import com.caiyu.entity.PageResult;
import com.caiyu.entity.QueryPageBean;
import com.caiyu.entity.Result;
import com.caiyu.pojo.Setmeal;
import com.caiyu.pojo.TSetmeal;
import com.caiyu.service.SetmealService;
import com.caiyu.utils.QiniuUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile")MultipartFile imgFile){
        try{


            String originalFilename = imgFile.getOriginalFilename();
            int index = originalFilename.indexOf(".");
            String suffix = originalFilename.substring(index - 1);
            String fileName = UUID.randomUUID().toString() + suffix;
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),fileName);
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);

        }
    }

    @RequestMapping("/add")
    public Result add(@RequestBody TSetmeal tSetmeal,@RequestParam(value = "checkgroupIds" ,required = false) List<Integer> checkgroupIds){
        try{

            //
            System.out.println(tSetmeal);

            setmealService.add(tSetmeal,checkgroupIds);
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        try {
            PageResult page = setmealService.findPage(queryPageBean);
            return page;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/findByIdConsumer")
    public Result findById(int id){
        try{
            Setmeal setmeal = setmealService.findById(id);
            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }
}
