package com.bobochang.rpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.bobochang.rpc.model.RpcRequest;
import com.bobochang.rpc.model.RpcResponse;
import com.bobochang.rpc.serializer.JdkSerializer;
import com.bobochang.rpc.serializer.Serializer;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author bobochang
 * @Description JDK 动态代理
 * @Date 2024/2/21 - 14:06
 */
public class ServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 对接口进行处理 根据要生成的对象类型 自动生成代理对象
        // - 指定序列化器
        Serializer serializer = new JdkSerializer();
        // - 构造请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            // - 序列化
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            byte[] result;
            // - 发送请求
            // todo 地址硬编码(需要使用注册中心和服务发现机制解决)
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()) {
                result = httpResponse.bodyBytes();
                // - 反序列化
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return rpcResponse.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
