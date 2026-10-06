package com.xiaocang.zhujieaop.controller;


import com.xiaocang.zhujieaop.pojo.User;
import com.xiaocang.zhujieaop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    UserService userService;


    @GetMapping("/user")
    private User getUser(){
        return userService.getUser();
    }
}
