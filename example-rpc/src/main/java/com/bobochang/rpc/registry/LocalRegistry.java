package com.bobochang.rpc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author bobochang
 * @Description 本地注册中心
 * @Date 2024/2/21 - 10:52
 */
public class LocalRegistry {
    /**
     * 存储注册信息的 map
     * key：服务名称
     * value：服务的实现类
     */
    private static final Map<String, Class<?>> map = new ConcurrentHashMap<>();

    /**
     * 服务注册
     *
     * @param serviceName 服务名称
     * @param implClass   服务的实现类
     */
    public static void register(String serviceName, Class<?> implClass) {
        map.put(serviceName, implClass);
    }

    /**
     * 根据服务名称获取服务的实现类
     *
     * @param serviceName 服务名称
     * @return 服务的实现类
     */
    public static Class<?> get(String serviceName) {
        return map.get(serviceName);
    }

    /**
     * 根据服务名称移除服务
     *
     * @param serviceName 服务名称
     */
    public static void remove(String serviceName) {
        map.remove(serviceName);
    }
}
