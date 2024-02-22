package com.bobochang.example.common.service;

import com.bobochang.example.common.model.User;

/**
 * @author bobochang
 * @Description 用户服务
 * @Date 2024/2/21 - 10:00
 */
public interface UserService {
    /**
     * 获取用户接口
     * @param user
     * @return
     */
    User getUser(User user);
}
