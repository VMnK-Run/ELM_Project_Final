package com.tju.elmboot.service;

import com.tju.elmboot.po.User;
import com.tju.elmboot.viewpo.UserInfo;

public interface UserService {

    public User getUserByIdByPass(User user);

    public int getUserById(String userId);

    public int saveUser(User user);

    public int updateUserById(User user);

    public UserInfo getUserInfoById(String userId);
}
