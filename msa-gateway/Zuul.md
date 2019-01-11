## 服务网关 Zuul

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

Zuul = 路由 + 过滤器  
Zuul的核心是一系列的过滤器
定义了四种过滤器API（Pre/Route/Post/Error）

Zuul组件架构图  
参考：
[深入理解Zuul之源码解析](https://blog.csdn.net/forezp/article/details/76211680)
![Zuul核心架构图](https://img-blog.csdnimg.cn/20181222164555235)

请求的生命周期  
![](https://images2015.cnblogs.com/blog/1099841/201706/1099841-20170630111344414-1260445909.png)

