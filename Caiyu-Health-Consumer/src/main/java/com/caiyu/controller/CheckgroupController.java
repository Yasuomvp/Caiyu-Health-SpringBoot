package com.caiyu.controller;

import com.caiyu.constant.MessageConstant;
import com.caiyu.entity.PageResult;
import com.caiyu.entity.QueryPageBean;
import com.caiyu.entity.Result;
import com.caiyu.pojo.CheckGroup;
import com.caiyu.service.CheckgroupService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkgroup")
public class CheckgroupController {

    @Reference
    private CheckgroupService checkgroupService;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        try {
            PageResult page = checkgroupService.findPage(queryPageBean);
            return page;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup,@RequestParam(value = "checkitemIds" ,required=false) List<Integer> checkItems){
        try {
            checkgroupService.add(checkGroup,checkItems);
            return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);

        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            CheckGroup group = checkgroupService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,group);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);

        }
    }

    @RequestMapping("/findCheckItemIdsByCheckgroupId")
    public Result findCheckItemIdsByCheckgroupId(Integer id){
        try {
            List<Integer> checkItems = checkgroupService.findCheckItems(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItems);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);

        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup,@RequestParam(value = "checkItemIds" ,required=false) List<Integer> checkItemIds){
        try {
            checkgroupService.edit(checkGroup,checkItemIds);
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);

        }
    }

    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            checkgroupService.deleteGroupById(id);
            return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }
}
