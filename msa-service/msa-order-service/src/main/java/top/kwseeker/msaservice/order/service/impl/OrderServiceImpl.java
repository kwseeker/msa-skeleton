package top.kwseeker.msaservice.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.kwseeker.msaservice.order.dao.OrderDAO;
import top.kwseeker.msaservice.order.entity.OrderEntity;
import top.kwseeker.msaservice.order.service.OrderService;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDAO, OrderEntity> implements OrderService {

    @Override
    public List<OrderEntity> listByUserId(Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        return baseMapper.selectList(queryWrapper);
    }
}
