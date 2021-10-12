package com.caiyu.dao;

import com.caiyu.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface OrderMapper {
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    @Insert("insert into t_order (member_id,orderDate,orderType,orderStatus,setmeal_id) values (#{memberId},#{orderDate},#{orderType},#{orderStatus},#{setmealId})")
    void add(Order order);

    @Select({
            "<script>" ,
            "select * from t_order",
            "<where>",
            "<if test= 'id != null' >",
            "and id = #{id}",
            "</if>",
            "<if test= 'memberId != null' >",
            "and member_id = #{memberId}",
            "</if>",
            "<if test= 'orderDate != null' >",
            "and date_format(t_order.orderDate, '%y-%m-%d') = date_format(#{orderDate}, '%y-%m-%d')",
            "</if>",
            "<if test= 'orderType != null' >",
            "and orderType = #{orderType}",
            "</if>",
            "<if test= 'orderStatus != null' >",
            "and orderStatus = #{orderStatus}",
            "</if>",
            "<if test= 'setmealId != null' >",
            "and member_id = #{setmealId}",
            "</if>",
            "</where>",
            "</script>"
    })
    List<Order> findByCondition(Order order);


    @Select("select m.name name ,s.name setmeal,o.orderDate orderDate,o.orderType orderType from t_order o,t_member m,t_setmeal s where o.member_id=m.id and o.setmeal_id=s.id and o.id=#{id}")
    Map findById4Detail(Integer id);
}
