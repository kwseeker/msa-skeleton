## Eureka Server

### 1 Eureka 功能

#### 1.1 心跳检测

#### 1.2 健康检查

#### 1.3 负载均衡

#### 1.4 服务注册与发现

+ 服务注册
+ 服务续约
+ 服务下线
+ 获取注册列表信息
+ 服务剔除

### 2 Eureka部署

#### 后台执行命令
```$xslt
nohup java -jar target/eureka-0.0.1-SNAPSHOT.jar > /dev/null 2 > &1 &
```

#### Eureka Server 高可用
通过部署两台以上的 Eureka Server 并互相注册实现；
注册到任意一台 Eureka Server 的微服务都会同步到其他 Server 上。

Eureka Server hostname 相同时，从另一个Eureka Server同步注册微服务会失败？

##### 开发环境：
可以在 application.yml 通过 spring.profiles 定义两个服务器配置，
设置相互注册。  
在IDEA中创建两个启动器，VM options分别指定：
```$xslt
-Dspring.profiles.active=EurekaServer1
-Dspring.profiles.active=EurekaServer2
```

##### 生产环境：
