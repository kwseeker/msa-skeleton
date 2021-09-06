package top.kwseeker.msaweb.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.kwseeker.msaweb.common.interceptor.ResponseResult;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping
@ResponseResult
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/findOrderByUserId/{id}")
    public Map<?, ?> findOrderByUserId(@PathVariable("id") Integer id) {
        log.info("根据userId:" + id + "查询订单信息");
        // RestTemplate调用    负载均衡器 mall-order服务&选择一个去调用
        // RestTemplate扩展点  ClientHttpRequestInterceptor
        // ribbon  LoadBalancerInterceptor
        //  mall-order  ---->    localhost: 8020
        // http://localhost: 8020 /order/findOrderByUserId/1
        String url = "http://mall-order/order/findOrderByUserId/" + id;
        return restTemplate.getForObject(url, Map.class);
    }

}
