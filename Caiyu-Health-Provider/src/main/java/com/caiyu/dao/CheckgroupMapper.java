package com.caiyu.dao;


import com.caiyu.pojo.CheckGroup;
import com.caiyu.pojo.CheckItem;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface CheckgroupMapper {

    @Select({
            "<script>" ,
            "select * from t_checkgroup",
            "<if test='queryString != null and queryString.length>0'>",
            "where code = #{queryString} or name=#{queryString} or helpCode=#{queryString}",
            "</if>",
            "</script>"
    })
    Page<CheckGroup> findPage(@Param("queryString") String queryString) throws Exception;

    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    @Insert("insert into t_checkgroup(code,name,helpCode,sex,remark,attention) values(#{code},#{name},#{helpCode},#{sex},#{remark},#{attention})")
    void add(CheckGroup checkGroup) throws Exception;

    @Insert("insert into t_checkgroup_checkitem(checkgroup_id,checkitem_id) values(#{checkgroupid},#{checkitemid})")
    void addgroup(Integer checkgroupid, Integer checkitemid) throws Exception;

    @Select("select * from t_checkgroup where id=#{id}")
    CheckGroup findById(Integer id) throws Exception;

    @Select("select checkitem_id from t_checkgroup_checkitem where checkgroup_id=#{id}")
    List<Integer> findCheckItems(Integer id) throws Exception;

    @Delete("delete from t_checkgroup_checkitem where checkgroup_id=#{id}")
    void deleteCheckitemsRelationsByGroupId(Integer id) throws Exception;

    @Delete(("delete from t_checkgroup where id=#{id}"))
    void deleteGroupById(Integer id) throws Exception;

    @Update({
            "<script>" ,
            "update t_checkgroup set",
            "<if test='code != null'>",
            "code = #{code},",
            "</if>",
            "<if test='name != null'>",
            "name = #{name},",
            "</if>",
            "<if test='helpCode != null'>",
            "helpCode = #{helpCode},",
            "</if>",
            "<if test='remark != null'>",
            "remark = #{remark},",
            "</if>",
            "<if test='attention != null'>",
            "attention = #{attention},",
            "</if>",
            "<if test='sex != null'>",
            "sex = #{sex}",
            "</if>",
            "where id = #{id}",
            "</script>"
    })
    void edit(CheckGroup checkGroup) throws Exception;

}
