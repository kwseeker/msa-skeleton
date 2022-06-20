package top.kwseeker.msa.seata.service;

import top.kwseeker.msa.seata.entity.Order;
import top.kwseeker.msa.seata.vo.OrderVo;

public interface OrderService {

    /**
     * 保存订单
     */
    Order saveOrder(OrderVo orderVo);
}
