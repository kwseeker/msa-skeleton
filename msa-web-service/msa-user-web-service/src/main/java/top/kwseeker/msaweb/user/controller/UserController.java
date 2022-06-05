package top.kwseeker.msaweb.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.kwseeker.msaweb.common.interceptor.ResponseWrap;
//import top.kwseeker.msaweb.user.interceptor.ResponseResult;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
//@ResponseResult
@ResponseWrap
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/findOrderByUserId/{id}")
    public Map<?, ?> findOrderByUserId(@PathVariable("id") Integer id) {
        log.info("根据userId:" + id + "查询订单信息");

        String url = "http://msa-order-service/order/findOrderByUserId/" + id;
        Map<?, ?> ret = restTemplate.getForObject(url, Map.class);
        //Map<String, String> ret = new HashMap<>();
        //ret.put("hello", "world");
        return ret;
    }

}
