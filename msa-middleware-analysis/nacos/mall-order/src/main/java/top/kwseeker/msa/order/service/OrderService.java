package top.kwseeker.msa.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.kwseeker.msa.common.utils.PageUtils;
import top.kwseeker.msa.order.entity.OrderEntity;

import java.util.List;
import java.util.Map;

public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<OrderEntity>  listByUserId(Integer userId);
}

