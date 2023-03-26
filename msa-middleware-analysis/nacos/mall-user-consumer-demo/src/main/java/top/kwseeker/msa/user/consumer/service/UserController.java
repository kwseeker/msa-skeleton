package top.kwseeker.msa.user.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.kwseeker.msa.common.utils.R;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    
    @Resource
    private RestTemplate restTemplate;
    
    @RequestMapping(value = "/findOrderByUserId/{id}")
    public R findOrderByUserId(@PathVariable("id") Integer id) {
        log.info("根据userId:"+id+"查询订单信息");
        // RestTemplate调用    负载均衡器 mall-order服务&选择一个去调用
        // RestTemplate扩展点  ClientHttpRequestInterceptor
        // ribbon  LoadBalancerInterceptor
        //  mall-order  ---->    localhost: 8020
        // http://localhost: 8020 /order/findOrderByUserId/1
        String url = "http://mall-order/order/findOrderByUserId/"+id;
        R result = restTemplate.getForObject(url,R.class);
        
        return result;
    }
}
