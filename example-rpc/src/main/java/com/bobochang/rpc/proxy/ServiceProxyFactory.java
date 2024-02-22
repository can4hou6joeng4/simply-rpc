package com.bobochang.rpc.proxy;

import java.lang.reflect.Proxy;

/**
 * @author bobochang
 * @Description 服务代理工厂(用于创建代理对象)
 * @Date 2024/2/21 - 14:22
 */
public class ServiceProxyFactory {
    public static <T> T getProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy());
    }
}
