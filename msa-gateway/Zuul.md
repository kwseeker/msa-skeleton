## 服务网关 Zuul

###1 网关简介

#### 网关设计要素
1）稳定高可用  
    因为网关是所有服务入口；  
2）性能要好，支持高并发  
3）安全性  
    可能需要添加加密措施  
4）拓展性要好  
    协议转发  
    流量统计  
    日志监控    

#### 常见的网关方案
+ Nginx + Lua
+ Kong（基于Nginx二次开发的组件）
+ Tyk（轻量级，Go语言实现）
+ Spring Cloud Zuul  
    使用Spring框架的话使用Zuul做网关开发很合适，
    唯一的缺点是性能比Nginx差。

实际使用可以综合使用各种网关，充分发挥各自的优势。

###2 Zuul简介
Zuul = 路由 + 过滤器  
Zuul的核心是一系列的过滤器
定义了四种过滤器API（Pre/Route/Post/Error）

Zuul的功能  
参考：[8. Router and Filter: Zuul](https://cloud.spring.io/spring-cloud-netflix/multi/multi__router_and_filter_zuul.html)  
Netflix uses Zuul for the following:
+ Authentication
+ Insights
+ Stress Testing
+ Canary Testing
+ Dynamic Routing
+ Service Migration
+ Load Shedding
+ Security
+ Static Response handling
+ Active/Active traffic management

Zuul组件架构图  
参考：  
[Zuul源码](https://github.com/Netflix/zuul)  
[深入理解Zuul之源码解析](https://blog.csdn.net/forezp/article/details/76211680)
![Zuul核心架构图](https://img-blog.csdnimg.cn/20181222164555235)

请求的生命周期  
![请求生命周期](https://images2015.cnblogs.com/blog/1099841/201706/1099841-20170630111344414-1260445909.png)

#### Zuul典型应用场景实现
前置过滤器（Pre）：
+ 限流  
    防止恶意请求攻击；  
    
    比较实用的限流算法有漏桶算法、令牌桶算法，还有一种计数器算法不过不太实用。
    
    漏桶作为计量工具时，可用于流量整形和流量控制，漏桶的主要概念如下：  
    一个固定容量的漏桶，按照常量固定速率流出水滴（流出请求）
    如果桶是空的，则不需流出水滴
    可以以任意速率流入水滴到漏桶（流入请求）
    如果流入水滴超出了桶的容量，则流入的水滴溢出了（新流入的请求被拒绝），则漏桶容量是不变的
    漏桶可以看做固定容量、固定流出速率的队列，漏桶限制的是请求的流出速率，漏桶中装的是请求。

    令牌桶算法是一个存放固定容量令牌（token）的桶，按照固定速率往桶里添加令牌。令牌桶的主要概念如下：  
    令牌按固定的速率被放入令牌桶中，例如：r tokens/秒
    桶中最多存放b个令牌，当桶满时，新添加的令牌被丢弃或拒绝
    当一个n字节大小的数据包到达，将从桶中删除n个令牌，接着数据包被发送到网络上
    如果桶中的令牌不足n个，则不会删除令牌，且数据包将被限流（丢弃或在缓冲区等待）
    令牌桶根据放令牌的速率（r tokens/s）去控制输出的速率（to network）。
    
    关于令牌桶限流算法可以参考下面两个开源实现：  
    [spring-cloud-zuul-ratelimit](https://github.com/marcosbarbero/spring-cloud-zuul-ratelimit)  
    
    [guava/src/com/google/common/util/concurrent/RateLimiter.java](https://github.com/google/guava/blob/master/guava/src/com/google/common/util/concurrent/RateLimiter.java)   
    Guava令牌桶限流的源码 RateLimiter.java、SmoothRateLimiter.java  
    
    
+ 鉴权
+ 参数校验与修改
+ 请求转发  
+ 跨域  
    跨域问题资料参考：  
    慕课网《Ajax跨域完全讲解》  

后置过滤器（Post）：
+ 统计（流量等）

#### Zuul实现高可用
方法1：只需要将多个Zuul节点注册到Eureka Server  
方法2：使用Nginx和Zuul混搭  


