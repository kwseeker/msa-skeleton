package top.kwseeker.msaservice.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.kwseeker.msaservice.order.entity.OrderEntity;

import java.util.List;

public interface OrderService extends IService<OrderEntity> {

    List<OrderEntity> listByUserId(Integer userId);
}
