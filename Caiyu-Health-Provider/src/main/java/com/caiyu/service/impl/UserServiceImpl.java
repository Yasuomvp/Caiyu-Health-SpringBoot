package com.caiyu.service.impl;

import com.caiyu.dao.PermissionMapper;
import com.caiyu.dao.RoleMapper;
import com.caiyu.dao.UserMapper;
import com.caiyu.pojo.Permission;
import com.caiyu.pojo.Role;
import com.caiyu.pojo.User;
import com.caiyu.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Component
@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public User findByUserName(String username) {
        User user = userMapper.findByUsername(username);
        if(user==null){
            return null;
        }
        Integer userId = user.getId();
        Set<Role> roles = roleMapper.findByUserId(userId);
        if(roles!=null && roles.size()>0){
            for(Role role:roles){
                Integer roleId = role.getId();
                Set<Permission> permissions = permissionMapper.findByRoleId(roleId);
                if(permissions!=null && permissions.size()>0){
                    role.setPermissions(permissions);
                }
            }
            user.setRoles(roles);
        }
        return user;
    }
}
