## TODO LIST

1）Eureka Server 认证
    Eureka client defaultZone URL如果嵌入了凭证，如 http://user:password@localhost:8761/eureka，
    则 HTTP BASIC 认证会被自动加入 Eureka client； 之后如何处理？
      
2）Eureka 注册实现原理

3）将各个模块的数据库映射类暴露出去有什么隐患？
感觉微服务一般都是在系统内部只是暴露给其他微服务应该没什么问题吧。
    
    
4）微服务对外暴露的接口放置问题，放在微服务本身模块，还是放在公共目录？  
    暂时选择放在微服务模块本身 <模块名>-api 目录中。
    
5）Zuul的动态路由配置

6）@ConfigurationProperties


