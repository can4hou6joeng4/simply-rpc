package com.bobochang.rpc.server;

import io.vertx.core.Vertx;

/**
 * @author bobochang
 * @Description
 * @Date 2024/2/21 - 10:34
 */
public class VertxHttpServer implements HttpServer {
    @Override
    public void doStart(int port) {
        // 1 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();
        // 2 创建 HTTP 服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();
        // 3 监听端口并处理请求
        server.requestHandler(new HttpServerHandler());
        // 4 启动 HTTP 服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("Server is now listening on port " + port);
            } else {
                System.err.println("Failed to start server:" + result.cause());
            }
        });
    }
}
