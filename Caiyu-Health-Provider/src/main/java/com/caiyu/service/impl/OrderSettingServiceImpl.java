package com.caiyu.service.impl;

import com.caiyu.dao.OrderSettingMapper;
import com.caiyu.pojo.OrderSetting;
import com.caiyu.service.OrderSettingService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;


@Transactional
@Service(interfaceClass = OrderSettingService.class)
@Component
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingMapper orderSettingMapper;

    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String lastDayOfMonth = sdf.format(cal.getTime());

        return lastDayOfMonth;
    }

    @Override
    public void add(List<OrderSetting> orderSettingList) throws Exception {
        if (orderSettingList != null && orderSettingList.size() > 0) {
            for (OrderSetting orderSetting : orderSettingList) {
                Long count = orderSettingMapper.findCountByOrderDate(orderSetting.getOrderDate());
                if (count > 0) {
                    orderSettingMapper.editNumberByOrderDate(orderSetting);
                } else {
                    orderSettingMapper.add(orderSetting);
                }
            }
        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) throws Exception {
        String[] split = date.split("-");
        String dateBegin = date + "-1";
        String dateEnd = date + "-" + getLastDayOfMonth(Integer.parseInt(split[0]), Integer.parseInt(split[1]));

        Map map = new HashMap();
        map.put("dateBegin", dateBegin);
        map.put("dateEnd", dateEnd);
        List<OrderSetting> list = orderSettingMapper.getOrderSettingByMonth(map);

        List<Map> data = new ArrayList<>();
        for (OrderSetting orderSetting : list) {
            Map orderSettingMap = new HashMap();
            orderSettingMap.put("date", orderSetting.getOrderDate().getDate());
            orderSettingMap.put("number", orderSetting.getNumber());
            orderSettingMap.put("reservations", orderSetting.getReservations());
            data.add(orderSettingMap);
        }
        return data;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) throws Exception {
        Long count = orderSettingMapper.findCountByOrderDate(orderSetting.getOrderDate());
        System.out.println(count);
        System.out.println(orderSetting);
        if (count > 0) orderSettingMapper.editNumberByOrderDate(orderSetting);
        else orderSettingMapper.add(orderSetting);
    }

}
