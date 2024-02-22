package com.bobochang.example.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.bobochang.example.common.model.User;
import com.bobochang.example.common.service.UserService;
import com.bobochang.rpc.model.RpcRequest;
import com.bobochang.rpc.model.RpcResponse;
import com.bobochang.rpc.serializer.JdkSerializer;

import java.io.IOException;

/**
 * @author bobochang
 * @Description
 * @Date 2024/2/21 - 13:54
 */
public class UserServiceProxy implements UserService {
    @Override
    public User getUser(User user) {
        // 构造 HTTP 请求调用服务提供者
        // - 指定序列化器
        JdkSerializer serializer = new JdkSerializer();
        // - 发送请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .args(new Object[]{user})
                .build();
        try {
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            byte[] result;
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()) {
                result = httpResponse.bodyBytes();
            }
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return (User) rpcResponse.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
