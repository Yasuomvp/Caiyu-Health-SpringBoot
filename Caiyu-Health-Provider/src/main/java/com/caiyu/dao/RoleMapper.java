package com.caiyu.dao;

import com.caiyu.pojo.Role;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface RoleMapper {
    @Select("select  r.* from t_role r ,t_user_role ur where r.id = ur.role_id and ur.user_id = #{userId}")
    Set<Role> findByUserId(int id);
}
