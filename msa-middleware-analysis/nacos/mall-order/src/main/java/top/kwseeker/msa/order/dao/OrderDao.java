package top.kwseeker.msa.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.kwseeker.msa.order.entity.OrderEntity;

@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
