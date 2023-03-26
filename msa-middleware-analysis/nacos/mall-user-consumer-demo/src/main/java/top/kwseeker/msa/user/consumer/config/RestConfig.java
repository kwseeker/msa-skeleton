package top.kwseeker.msa.user.consumer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Configuration
public class RestConfig {
    
    @Resource
    private LoadBalancerClient loadBalancer;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
        //RestTemplate restTemplate = new RestTemplate();
        //restTemplate.setInterceptors(Collections.singletonList(new LoadBalancerInterceptor(loadBalancer)));
        //return restTemplate;
    }
}
