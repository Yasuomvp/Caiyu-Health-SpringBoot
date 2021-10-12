package com.caiyu.dao;


import com.caiyu.pojo.OrderSetting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface OrderSettingMapper {

    @Insert("insert into t_ordersetting (orderDate,number,reservations) values (#{orderDate},#{number},#{reservations})")
    void add(OrderSetting orderSetting) throws Exception;

    @Update("update t_ordersetting set number = #{number} where date_format(t_ordersetting.orderDate, '%y-%m-%d') = date_format(#{orderDate}, '%y-%m-%d');")
    void editNumberByOrderDate(OrderSetting orderSetting) throws Exception;

    @Select("select count(*) from t_ordersetting where date_format(t_ordersetting.orderDate, '%y-%m-%d') = date_format(#{date}, '%y-%m-%d');")
    Long findCountByOrderDate(Date date) throws Exception;

    @Select("select * from t_ordersetting where orderDate between #{dateBegin} and #{dateEnd}")
    List<OrderSetting> getOrderSettingByMonth(Map map) throws Exception;

    @Select("select * from t_ordersetting where date_format(t_ordersetting.orderDate, '%y-%m-%d') = date_format(#{date}, '%y-%m-%d');")
    OrderSetting findByOrderDate(Date orderDate) throws Exception;

    @Update("update t_ordersetting set reservations = #{reservations} where date_format(t_ordersetting.orderDate, '%y-%m-%d') = date_format(#{orderDate}, '%y-%m-%d');")
    void editResrvationsByOrderDate(OrderSetting orderSetting);
}
