package com.xiaocang.zhujieaop.service;


import com.xiaocang.zhujieaop.annotation.RateLimit;
import com.xiaocang.zhujieaop.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {



    @RateLimit(limit = 3)
    public User getUser() {
        User user = new User();
        user.setId(0L);
        user.setName("csn");
        user.setPhone("18888888888");

        return user;
    }
}
