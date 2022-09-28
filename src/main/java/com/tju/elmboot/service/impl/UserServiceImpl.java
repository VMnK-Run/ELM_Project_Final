package com.tju.elmboot.service.impl;

import com.tju.elmboot.mapper.UserMapper;
import com.tju.elmboot.po.User;
import com.tju.elmboot.service.UserService;
import com.tju.elmboot.viewpo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByIdByPass(User user) {
        return userMapper.getUserByIdByPass(user);
    }

    @Override
    public int getUserById(String userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public int saveUser(User user) {
        return userMapper.saveUser(user);
    }

    @Override
    public int updateUserById(User user) {
        return userMapper.updateUserById(user);
    }

    @Override
    public UserInfo getUserInfoById(String userId) {
        User user = userMapper.getUserInfoById(userId);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getUserId());
        userInfo.setUserName(user.getUserName());
        userInfo.setUserSex(user.getUserSex());
        return userInfo;
    }
}
