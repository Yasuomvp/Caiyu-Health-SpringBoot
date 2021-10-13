package com.caiyu.dao;

import com.caiyu.pojo.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from t_user where username = #{username}")
    User findByUsername(String username);
}
