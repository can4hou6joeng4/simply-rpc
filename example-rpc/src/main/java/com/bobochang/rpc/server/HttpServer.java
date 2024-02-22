package com.bobochang.rpc.server;

/**
 * @author bobochang
 * @Description Http服务器接口
 * @Date 2024/2/21 - 10:32
 */
public interface HttpServer {
    /**
     * 启动服务器接口
     *
     * @param port 指定服务器端口
     */
    void doStart(int port);
}
