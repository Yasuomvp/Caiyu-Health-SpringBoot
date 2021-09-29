package com.caiyu.dao;

import com.caiyu.pojo.TSetmeal;
import com.caiyu.pojo.TSetmealExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface TSetmealMapper {
    long countByExample(TSetmealExample example);

    int deleteByExample(TSetmealExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSetmeal record);

    int insertSelective(TSetmeal record);

    List<TSetmeal> selectByExample(TSetmealExample example);

    TSetmeal selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSetmeal record, @Param("example") TSetmealExample example);

    int updateByExample(@Param("record") TSetmeal record, @Param("example") TSetmealExample example);

    int updateByPrimaryKeySelective(TSetmeal record);

    int updateByPrimaryKey(TSetmeal record);
}