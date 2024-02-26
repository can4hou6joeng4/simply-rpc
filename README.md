## Simple RPC Framework

### 架构设计图

![截屏2024-02-26 14.36.22](https://bobocahng-1309945187.cos.ap-guangzhou.myqcloud.com/bobochang's%20Mac/202402261516263.jpg)

### 目录结构介绍

![截屏2024-02-26 15.19.46](https://bobocahng-1309945187.cos.ap-guangzhou.myqcloud.com/bobochang's%20Mac/202402261520919.jpg)

- example-common：示例代码的公共模块
  - model：数据模型
  - service：服务相关接口
- example-consumer：示例服务消费者代码
- example-provider：示例服务提供者的代码
- simply-rpc：简易版 RPC 框架
  - model：数据模型
  - proxy：动态代理
  - registry：本地服务注册器
  - serializer：序列化/反序列化器
  - server：请求处理器