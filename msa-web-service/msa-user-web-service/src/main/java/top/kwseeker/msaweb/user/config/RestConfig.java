package top.kwseeker.msaweb.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Nacos服务发现之负载均衡原理：
 *  拓展了Spring RestTemplate 的 ClientHttpRequestInterceptor。在这个拦截器里面依赖Netflix Ribbon组件引入了负载均衡策略。
 *  Ribbon中通过LoadBalancerInterceptor实现了负载均衡策略。
 * 使用Nacos过程中我们不需要操作RestTemplate拓展拦截器。都是Nacos自动装配的。
 * 但是也可以手动拓展拦截器达到上面效果。
 */
@Configuration
public class RestConfig {


    //导入的Ribbon的负载均衡器
    @Autowired
    LoadBalancerClient loadBalancer;

    /**
     * 手动拓展拦截器
     */
    //@Bean
    //public RestTemplate restTemplate() {
    //    RestTemplate restTemplate = new RestTemplate();
    //    restTemplate.setInterceptors(Collections.singletonList(new LoadBalancerInterceptor(loadBalancer)));
    //    return restTemplate;
    //}

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
