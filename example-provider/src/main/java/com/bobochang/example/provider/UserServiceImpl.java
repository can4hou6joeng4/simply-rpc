package com.bobochang.example.provider;

import com.bobochang.example.common.model.User;
import com.bobochang.example.common.service.UserService;

/**
 * @author bobochang
 * @Description 用户服务实现类
 * @Date 2024/2/21 - 10:07
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
