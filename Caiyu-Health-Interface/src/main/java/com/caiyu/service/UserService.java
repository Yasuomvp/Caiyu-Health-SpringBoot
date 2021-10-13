package com.caiyu.service;

import com.caiyu.pojo.User;

public interface UserService {
    public User findByUserName(String username);
}
