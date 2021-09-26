package com.caiyu.dao;


import com.caiyu.pojo.CheckItem;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface CheckitemMapper {

    @Insert("insert into t_checkitem(code,name,sex,age,price,type,attention,remark) values(#{code},#{name},#{sex},#{age},#{price},#{type},#{attention},#{remark})")
    void add(CheckItem checkItem) throws Exception;

    @Select({
            "<script>" ,
            "select * from t_checkitem",
            "<if test='queryString != null and queryString.length>0'>",
            "where code = #{queryString} or name=#{queryString}",
            "</if>",
            "</script>"
    })
    Page<CheckItem> selectByItem(@Param("queryString") String queryString) throws Exception;

    @Select("select count(*) from t_checkgroup_checkitem where checkgroup_id = #{id}")
    Long findCountByCheckitemId(Integer id) throws Exception;

    @Delete("delete from t_checkitem where id = #{id}")
    void deleteById(Integer id) throws Exception;

    @Update({
            "<script>" ,
            "update t_checkitem set",
            "<if test='code != null'>",
            "code = #{code},",
            "</if>",
            "<if test='sex != null'>",
            "sex = #{sex},",
            "</if>",
            "<if test='age != null'>",
            "age = #{age},",
            "</if>",
            "<if test='price != null'>",
            "price = #{price},",
            "</if>",
            "<if test='type != null'>",
            "type = #{type},",
            "</if>",
            "<if test='attention != null'>",
            "attention = #{attention},",
            "</if>",
            "<if test='remark != null'>",
            "remark = #{remark},",
            "</if>",
            "<if test='name != null'>",
            "name = #{name}",
            "</if>",
            "where id = #{id}",
            "</script>"
    })
    void edit(CheckItem checkItem) throws Exception;

    @Select("select * from t_checkitem where id = #{id}")
    CheckItem findById(Integer id) throws Exception;

    @Select("select * from t_checkitem")
    List<CheckItem> findAll() throws Exception;
}
