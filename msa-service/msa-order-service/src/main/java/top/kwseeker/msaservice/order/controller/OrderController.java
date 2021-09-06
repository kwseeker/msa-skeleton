package top.kwseeker.msaservice.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kwseeker.msaservice.order.entity.OrderEntity;
import top.kwseeker.msaservice.order.service.OrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/findOrderByUserId/{userId}")
    public Map<String, List<OrderEntity>>  findOrderByUserId(@PathVariable("userId") Integer userId) {
        //try {
        //    Thread.sleep(8000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //模拟异常
        //if(userId==5){
        //    throw new IllegalArgumentException("非法参数异常");
        //}

        log.info("根据userId:" + userId + "查询订单信息");
        List<OrderEntity> orderEntities = orderService.listByUserId(userId);
        Map<String, List<OrderEntity>> resultMap = new HashMap<>();
        resultMap.put("orders", orderEntities);
        return resultMap;
    }
}
