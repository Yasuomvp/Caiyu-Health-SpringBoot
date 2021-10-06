package com.caiyu.dao;


import com.caiyu.pojo.CheckItem;
import com.caiyu.pojo.Setmeal;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface JustSetmealMapper {

    @Select({
            "<script>" ,
            "select * from t_setmeal",
            "<if test='queryString != null and queryString.length>0'>",
            "where code = #{queryString} or name=#{queryString} or helpCode=#{queryString}",
            "</if>",
            "</script>"
    })
    Page<Setmeal> selectByQuerryString(@Param("queryString") String queryString) throws Exception;

    @Select("select * from t_setmeal")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(
                    column = "id",
                    property = "checkGroups",
                    javaType = List.class,
                    many = @Many(select = "com.caiyu.dao.CheckgroupMapper.findById_Many")
            )
    })
    List<Setmeal> findAll() throws Exception;


    @Select("select * from t_setmeal where id=#{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(
                    column = "id",
                    property = "checkGroups",
                    javaType = List.class,
                    many = @Many(select = "com.caiyu.dao.CheckgroupMapper.findById_Many")
            )
    })
    Setmeal findById(Integer id) throws Exception;

}
