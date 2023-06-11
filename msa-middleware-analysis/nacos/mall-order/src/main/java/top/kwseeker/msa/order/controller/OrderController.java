package top.kwseeker.msa.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.kwseeker.msa.common.utils.PageUtils;
import top.kwseeker.msa.common.utils.R;
import top.kwseeker.msa.order.entity.OrderEntity;
import top.kwseeker.msa.order.service.OrderService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 根据用户id查询订单信息
     * @param userId
     * @return
     */
    @RequestMapping("/findOrderByUserId/{userId}")
    public R findOrderByUserId(@PathVariable("userId") Integer userId) {

        //try {
        //    Thread.sleep(8000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //模拟异常
        //if(userId==5){
        //    throw new IllegalArgumentException("非法参数异常");
        //}

        log.info("根据userId:"+userId+"查询订单信息");
        List<OrderEntity> orderEntities = orderService.listByUserId(userId);
        return R.ok().put("orders", orderEntities);
    }

    /**
     * 测试gateway
     */
    //@GetMapping("/testgateway")
    //public String testGateway(HttpServletRequest request) throws Exception {
    //    log.info("gateWay获取请求头X-Request-color："
    //            +request.getHeader("X-Request-color"));
    //    return "success";
    //}
    //@GetMapping("/testgateway2")
    //public String testGateway(@RequestHeader("X-Request-color") String color) throws Exception {
    //    log.info("gateWay获取请求头X-Request-color："+color);
    //    return "success";
    //}
    //@GetMapping("/testgateway3")
    //public String testGateway3(@RequestParam("color") String color) throws Exception {
    //    log.info("gateWay获取请求参数color:"+color);
    //    return "success";
    //}

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		OrderEntity order = orderService.getById(id);
        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody OrderEntity order){
		orderService.save(order);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody OrderEntity order){
		orderService.updateById(order);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		orderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}
