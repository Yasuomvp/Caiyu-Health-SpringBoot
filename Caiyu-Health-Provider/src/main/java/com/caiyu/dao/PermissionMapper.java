package com.caiyu.dao;

import com.caiyu.pojo.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface PermissionMapper {
    @Select("select p.* from t_permission p ,t_role_permission rp where p.id = rp.permission_id and rp.role_id = #{roleId}")
    Set<Permission> findByRoleId(int roleId);
}
