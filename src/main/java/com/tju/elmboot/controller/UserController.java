package com.tju.elmboot.controller;

import com.tju.elmboot.po.User;
import com.tju.elmboot.service.UserService;
import com.tju.elmboot.viewpo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/UserController")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("getUserByIdByPass")
    public User getUserByIdByPass(User user) throws Exception {
        return userService.getUserByIdByPass(user);
    }

    @RequestMapping("getUserById")
    public int getUserById(User user) throws Exception {
        return userService.getUserById(user.getUserId());
    }

    @RequestMapping("/saveUser")
    public int saveUser(User user) throws Exception {
        return userService.saveUser(user);
    }

    @RequestMapping("/updateUserById")
    public int updateUserById(User user) throws Exception {
        return userService.updateUserById(user);
    }

    @RequestMapping("/getUserInfoById")
    public UserInfo getUserInfoById(String userId) throws Exception {
        return userService.getUserInfoById(userId);
    }
}
