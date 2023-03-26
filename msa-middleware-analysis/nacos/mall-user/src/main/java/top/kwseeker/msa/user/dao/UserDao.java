package top.kwseeker.msa.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.kwseeker.msa.user.entity.UserEntity;

@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
