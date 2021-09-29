package com.caiyu.dao;


import com.caiyu.pojo.CheckItem;
import com.caiyu.pojo.Setmeal;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

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


}
