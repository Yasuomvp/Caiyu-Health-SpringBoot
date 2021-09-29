package com.caiyu.dao;

import com.caiyu.pojo.TSetmealCheckgroupExample;
import com.caiyu.pojo.TSetmealCheckgroupKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface TSetmealCheckgroupMapper {
    long countByExample(TSetmealCheckgroupExample example);

    int deleteByExample(TSetmealCheckgroupExample example);

    int deleteByPrimaryKey(TSetmealCheckgroupKey key);

    int insert(TSetmealCheckgroupKey record);

    int insertSelective(TSetmealCheckgroupKey record);

    List<TSetmealCheckgroupKey> selectByExample(TSetmealCheckgroupExample example);

    int updateByExampleSelective(@Param("record") TSetmealCheckgroupKey record, @Param("example") TSetmealCheckgroupExample example);

    int updateByExample(@Param("record") TSetmealCheckgroupKey record, @Param("example") TSetmealCheckgroupExample example);
}