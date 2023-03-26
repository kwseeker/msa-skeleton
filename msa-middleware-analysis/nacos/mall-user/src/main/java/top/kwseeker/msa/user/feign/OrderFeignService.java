//package top.kwseeker.msa.user.feign;
//
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import top.kwseeker.msa.common.utils.R;
//
//@FeignClient(value = "mall-order",path = "/order")
//public interface OrderFeignService {
//
//    @RequestMapping("/findOrderByUserId/{userId}")
//    public R findOrderByUserId(@PathVariable("userId") Integer userId);
//}