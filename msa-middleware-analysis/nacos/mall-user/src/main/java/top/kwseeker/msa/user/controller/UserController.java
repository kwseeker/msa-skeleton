package top.kwseeker.msa.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import top.kwseeker.msa.common.utils.PageUtils;
import top.kwseeker.msa.common.utils.R;
import top.kwseeker.msa.user.entity.UserEntity;
import top.kwseeker.msa.user.service.UserService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private RestTemplate restTemplate;
    //@Resource
    //OrderFeignService orderFeignService;

    @RequestMapping(value = "/findOrderByUserId/{id}")
    public R findOrderByUserId(@PathVariable("id") Integer id) {
        log.info("根据userId:"+id+"查询订单信息");
        // RestTemplate调用
        //String url = "http://localhost:8020/order/findOrderByUserId/"+id;
        //R result = restTemplate.getForObject(url,R.class);

        //模拟ribbon实现
        //String url = getUri("mall-order")+"/order/findOrderByUserId/"+id;
        // 添加@LoadBalanced
        String url = "http://mall-order/order/findOrderByUserId/"+id;
        R result = restTemplate.getForObject(url, R.class);

        //feign调用
        //R result = orderFeignService.findOrderByUserId(id);

        return result;
    }

    //@Resource
    //private DiscoveryClient discoveryClient;
    //public String getUri(String serviceName) {
    //    List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
    //    if (serviceInstances == null || serviceInstances.isEmpty()) {
    //        return null;
    //    }
    //    int serviceSize = serviceInstances.size();
    //    //轮询
    //    int indexServer = incrementAndGetModulo(serviceSize);
    //    return serviceInstances.get(indexServer).getUri().toString();
    //}
    //private AtomicInteger nextIndex = new AtomicInteger(0);
    //private int incrementAndGetModulo(int modulo) {
    //    for (;;) {
    //        int current = nextIndex.get();
    //        int next = (current + 1) % modulo;
    //        if (nextIndex.compareAndSet(current, next) && current < modulo){
    //            return current;
    //        }
    //    }
    //}

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        UserEntity user = userService.getById(id);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UserEntity user){
        userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserEntity user){
        userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        userService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}
