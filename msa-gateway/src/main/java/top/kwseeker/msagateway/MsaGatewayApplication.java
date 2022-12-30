package top.kwseeker.msagateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@EnableConfigurationProperties(UriConfiguration.class)
@RestController
public class MsaGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaGatewayApplication.class, args);
    }

    /**
     * 创建路由（使用Java API的方式）
     * TODO 这里的 RouteLocatorBuilder Bean实例化流程
     */
    //@Bean
    //public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
    //    String httpUri = uriConfiguration.getBackServiceDomain();
    //    return builder.routes()
    //            // 看官方文档这里的 route 可能是装饰器模式，TODO 核对下
    //            .route(p -> p
    //                    .path("/hello", "/slow", "/get")
    //                    .filters(f -> f.addRequestHeader("Hello", "World"))
    //                    .uri(httpUri))
    //            .route(p -> p
    //                    .host("*.circuitbreaker.com")
    //                    .filters(f -> f
    //                            .circuitBreaker(config -> config
    //                                    .setName("break1")
    //                                    .setFallbackUri("forward:/fallback")))
    //                    .uri(httpUri))
    //            .build();
    //}

    //@RequestMapping("/fallback")
    //public Mono<String> fallback() {
    //    System.out.println("call fallback()...");
    //    return Mono.just("fallback");
    //}
}

//@ConfigurationProperties(prefix = "prefix")
//class UriConfiguration {
//
//    private String backServiceDomain = "http://localhost:8080";
//
//    public String getBackServiceDomain() {
//        return backServiceDomain;
//    }
//
//    public void setBackServiceDomain(String backServiceDomain) {
//        this.backServiceDomain = backServiceDomain;
//    }
//}
