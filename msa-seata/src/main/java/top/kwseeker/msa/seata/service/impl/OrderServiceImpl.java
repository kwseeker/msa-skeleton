package top.kwseeker.msa.seata.service.impl;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.kwseeker.msa.seata.config.DataSourceKey;
import top.kwseeker.msa.seata.config.DynamicDataSourceContextHolder;
import top.kwseeker.msa.seata.entity.Order;
import top.kwseeker.msa.seata.entity.OrderStatus;
import top.kwseeker.msa.seata.mapper.OrderMapper;
import top.kwseeker.msa.seata.service.AccountService;
import top.kwseeker.msa.seata.service.OrderService;
import top.kwseeker.msa.seata.service.StorageService;
import top.kwseeker.msa.seata.vo.OrderVo;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final AccountService accountService;
    private final StorageService storageService;

    public OrderServiceImpl(OrderMapper orderMapper, AccountService accountService, StorageService storageService) {
        this.orderMapper = orderMapper;
        this.accountService = accountService;
        this.storageService = storageService;
    }

    //@Transactional
    @GlobalTransactional(name="createOrder")
    @Override
    public Order saveOrder(OrderVo orderVo) {
        log.info("=============用户下单=================");

        //切换数据源
        DynamicDataSourceContextHolder.setDataSourceKey(DataSourceKey.ORDER);
        //log.info("当前 XID: {}", RootContext.getXID());

        // 保存订单
        Order order = new Order();
        order.setUserId(orderVo.getUserId());
        order.setCommodityCode(orderVo.getCommodityCode());
        order.setCount(orderVo.getCount());
        order.setMoney(orderVo.getMoney());
        order.setStatus(OrderStatus.INIT.getValue());

        int saveOrderRecord = orderMapper.insert(order);
        log.info("保存订单{}", saveOrderRecord > 0 ? "成功" : "失败");

        //扣减库存
        storageService.deduct(orderVo.getCommodityCode(),orderVo.getCount());

        //扣减余额
        accountService.debit(orderVo.getUserId(),orderVo.getMoney());

        log.info("=============更新订单状态=================");
        //切换数据源
        DynamicDataSourceContextHolder.setDataSourceKey(DataSourceKey.ORDER);
        //更新订单
        int updateOrderRecord = orderMapper.updateOrderStatus(order.getId(),OrderStatus.SUCCESS.getValue());
        log.info("更新订单id:{} {}", order.getId(), updateOrderRecord > 0 ? "成功" : "失败");

        return order;
    }
}
