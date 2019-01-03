package top.kwseeker.msatest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import top.kwseeker.msatest.client.MsaCrontabClient;

@RestController
@RequestMapping("/test")
public class CrontabTestController {

    private static final Logger logger = LoggerFactory.getLogger(CrontabTestController.class);

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MsaCrontabClient msaCrontabClient;

    //TODO：复杂数据类型，怎么在微服务间传递
//    @PostMapping("/addJob")
//    public String addJob() {
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.postForObject("http://localhost:9011/jobs/add");
//    }

    @GetMapping("/listJob/{meansId}")
    public String listJob(@PathVariable("meansId") Integer meansId) {
        String response = "";
        logger.info("meansId={}", meansId);
        switch (meansId) {
            case 1:
                //方法1
                RestTemplate restTemplate1 = new RestTemplate();
                response = restTemplate1.getForObject("http://localhost:9011/jobs/list", String.class);
                break;
            case 2:
                //方法2
                //支持一个微服务多实例的负载均衡
                //TODO：Ribbon的负载均衡实现原理
                RestTemplate restTemplate2 = new RestTemplate();
                ServiceInstance serviceInstance = loadBalancerClient.choose("MSA-CRONTAB");
                String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/jobs/list";
                response = restTemplate2.getForObject(url, String.class);
                break;
            case 3:
                //方法3，实现和方法2是一样的
                response = restTemplate.getForObject("http://MSA-CRONTAB/jobs/list", String.class);
                break;
            case 4:
                //方法4，使用feign, 这种方法输出的格式也最规范（Json，其他是xml）
                response = msaCrontabClient.listJob();
                break;
            default:
        }

        logger.info("response = {}", response);
        return response;
    }



}
