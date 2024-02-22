package com.bobochang.example.provider;

import com.bobochang.example.common.service.UserService;
import com.bobochang.rpc.registry.LocalRegistry;
import com.bobochang.rpc.server.HttpServer;
import com.bobochang.rpc.server.VertxHttpServer;

/**
 * @author bobochang
 * @Description 简易服务提供者启动类
 * @Date 2024/2/21 - 10:09
 */
public class EasyProviderExample {
    public static void main(String[] args) {
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
        // 启动 web 服务
        HttpServer server = new VertxHttpServer();
        server.doStart(8080);
    }
}
