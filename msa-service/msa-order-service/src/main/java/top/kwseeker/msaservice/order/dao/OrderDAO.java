package top.kwseeker.msaservice.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.kwseeker.msaservice.order.entity.OrderEntity;

@Mapper
public interface OrderDAO extends BaseMapper<OrderEntity> {

}