package com.bobochang.rpc.server;

import com.bobochang.rpc.model.RpcRequest;
import com.bobochang.rpc.model.RpcResponse;
import com.bobochang.rpc.registry.LocalRegistry;
import com.bobochang.rpc.serializer.JdkSerializer;
import io.netty.handler.codec.http.HttpRequest;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author bobochang
 * @Description 请求处理器
 * @Date 2024/2/21 - 11:22
 */
public class HttpServerHandler implements Handler<HttpServerRequest> {
    @Override
    public void handle(HttpServerRequest request) {
        // 1 反序列化请求为对象 并从请求对象中获取参数
        // - 指定序列化器
        final JdkSerializer serializer = new JdkSerializer();
        // - 记录日志
        System.out.println("Received request:" + request.method() + " " + request.uri());
        // - 异步处理请求
        request.bodyHandler(body -> {
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try {
                rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // - 构造响应结果对象
            RpcResponse rpcResponse = new RpcResponse();
            // - 判断请求是否未 null
            if (rpcRequest == null) {
                rpcResponse.setMessage("rpcRequest is null");
                doResponse(request, rpcResponse, serializer);
                return;
            }
            // 2 根据服务名称从本地注册器中获取到对应的服务实现类
            try {
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                // 3 通过反射机制调用方法 得到结果
                Object result = method.invoke(implClass.newInstance(), rpcRequest.getArgs());
                // 4 对返回结果进行封装和序列化 并写入响应中
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("ok");
            } catch (Exception e) {
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }
            doResponse(request, rpcResponse, serializer);
        });
    }

    /**
     * 执行响应
     *
     * @param request
     * @param rpcResponse
     * @param serializer
     */
    void doResponse(HttpServerRequest request, RpcResponse rpcResponse, JdkSerializer serializer) {
        HttpServerResponse httpServerResponse = request.response()
                .putHeader("content-type", "application/json");
        try {
            byte[] serialized = serializer.serialize(rpcResponse);
            httpServerResponse.end(Buffer.buffer(serialized));
        } catch (IOException e) {
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }
    }
}
