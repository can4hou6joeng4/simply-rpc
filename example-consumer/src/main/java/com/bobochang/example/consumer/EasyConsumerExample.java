package com.bobochang.example.consumer;

import com.bobochang.example.common.model.User;
import com.bobochang.example.common.service.UserService;
import com.bobochang.rpc.proxy.ServiceProxyFactory;

/**
 * @author bobochang
 * @Description 简易服务消费者启动类
 * @Date 2024/2/21 - 10:13
 */
public class EasyConsumerExample {
    public static void main(String[] args) {
        // 获取 UserService 的实现类对象
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("bobochang");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}
