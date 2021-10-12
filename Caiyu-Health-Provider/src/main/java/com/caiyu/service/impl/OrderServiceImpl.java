package com.caiyu.service.impl;

import com.caiyu.constant.MessageConstant;
import com.caiyu.dao.MemberMapper;
import com.caiyu.dao.OrderMapper;
import com.caiyu.dao.OrderSettingMapper;
import com.caiyu.entity.Result;
import com.caiyu.pojo.Member;
import com.caiyu.pojo.Order;
import com.caiyu.pojo.OrderSetting;
import com.caiyu.service.OrderService;
import com.caiyu.utils.DateUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.management.ThreadInfo;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderSettingMapper orderSettingMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Result order(Map map) throws Exception {
        //检查当前日期是否有预约设置
        String orderDate = (String) map.get("orderDate");
        Date date = DateUtils.parseString2Date(orderDate);
        OrderSetting orderSetting = orderSettingMapper.findByOrderDate(date);
        if(orderSetting == null){
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }

        //检查预约日期是否已满
        int number = orderSetting.getNumber();//可预约人数
        int reservations = orderSetting.getReservations();//已预约人数
        if(reservations >= number){
            return new Result(false,MessageConstant.ORDER_FULL);
        }
        //用手机号检查当前用户是否为会员
        String telephone = (String) map.get("telephone");
        Member member = memberMapper.findByTelephone(telephone);
        //防止重复预约
        if(member!=null){
            Integer memberId = member.getId();
            int setmealId = Integer.parseInt((String)map.get("setmealId"));
            Order order = new Order(memberId,date,null,null,setmealId);
            List<Order> orderList = orderMapper.findByCondition(order);
            if(orderList!=null && orderList.size()>0){
                return new Result(false,MessageConstant.HAS_ORDERED);
            }
        }

        orderSetting.setReservations(orderSetting.getReservations()+1);
        orderSettingMapper.editResrvationsByOrderDate(orderSetting);

        if(member==null){
            member = new Member();
            member.setName((String) map.get("name"));
            member.setPhoneNumber(telephone);
            member.setIdCard((String) map.get("idCard"));
            member.setSex((String) map.get("sex"));
            member.setRegTime(new Date());
            memberMapper.add(member);

        }

        Order order = new Order(
                member.getId(),
                date,
                (String) map.get("orderType"),
                Order.ORDERSTATUS_NO,
                Integer.parseInt((String) map.get("setmealId"))
        );

        orderMapper.add(order);

        return new Result(true,MessageConstant.ORDER_SUCCESS,order.getId());


    }

    @Override
    public Map findById(Integer id) throws Exception {
        Map map = orderMapper.findById4Detail(id);
        if(map!=null){
            Date orderDate = (Date) map.get("orderDate");
            map.put("orderDate",DateUtils.parseDate2String(orderDate));
        }
        return map;
    }
}
