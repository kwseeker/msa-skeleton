# Nacos 应用篇

[Nacos docs](https://nacos.io/zh-cn/docs/what-is-nacos.html)

## 引入Nacos注册中心

1. **安装Nacos Server服务并启动**

   下载启动参考: [Nacos快速开始](https://nacos.io/zh-cn/docs/quick-start.html)

   ```shell
   # 下载后解压到/opt
   sudo ln -s nacos-2.0.3 nacos
   vi ~/.zshrc
       # Nacos-2.0.3
       export NACOS_HOME=/opt/nacos
       export PATH=$NACOS_HOME/bin:$PATH
   source ~/.zshrc
   
   echo fs.inotify.max_user_watches=524288 | sudo tee -a /etc/sysctl.conf && sudo sysctl -p
   ```

   启动&关闭

   ```shell
   # 启动时可能遇到：ErrCode:500, ErrMsg:User limit of inotify watches reached Caused by: java.io.IOException: User limit of inotify watches reached
   #  inotify是一个内核用于通知用户空间程序文件系统变化的机制，如文件增加、删除等事件可以立刻让用户态得知
   cat /proc/sys/fs/inotify/max_user_watches
   echo fs.inotify.max_user_watches=524288 | sudo tee -a /etc/sysctl.conf && sudo sysctl -p
   # 单机模式
   nacos-startup.sh -m standalone
   # 集群模式
   
   # 启动成功日志（默认监听8848）
   2021-09-05 12:18:26,284 INFO Exposing 16 endpoint(s) beneath base path '/actuator'
   2021-09-05 12:18:26,374 INFO Tomcat started on port(s): 8848 (http) with context path '/nacos'
   2021-09-05 12:18:26,377 INFO Nacos started successfully in stand alone mode. use embedded storage
   # 检查
   curl -X GET 'http://127.0.0.1:8848/nacos/actuator'
   ```

   docker容器启动

   ```shell
   
   ```

2. **微服务引入Nacos客户端**

   参考：[Nacos dicovery](https://github.com/alibaba/spring-cloud-alibaba/wiki/Nacos-discovery)

   **服务注册&发现接口**

   ```shell
   curl -X POST 'http://127.0.0.1:8848/nacos/v1/ns/instance?serviceName=nacos.naming.serviceName&ip=20.18.7.10&port=8080'
   curl -X GET 'http://127.0.0.1:8848/nacos/v1/ns/instance/list?serviceName=nacos.naming.serviceName'
   ```

   **引入 Nacos Discovery流程: **

   **a) 引入 Nacos Discovery Starter**

   ```xml
   <dependency>
       <groupId>com.alibaba.cloud</groupId>
       <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
   </dependency>
   ```

   **b) 配置 Nacos 服务端**

   **c) 配置Nacos Discovery 客户端**

   ```yaml
   spring:
     # Nacos 注册中心地址
     cloud:
       nacos:
         # 更多配置参考：NacosDiscoveryProperties.java
         # 比如：配置权限（username, password）、分组等
         discovery:
         　server-addr: 127.0.0.1:8848
           group: web-service
   ```

   

## 引入Nacos配置中心

