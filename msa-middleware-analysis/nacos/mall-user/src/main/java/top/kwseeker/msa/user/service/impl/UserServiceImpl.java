package top.kwseeker.msa.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.kwseeker.msa.common.utils.PageUtils;
import top.kwseeker.msa.common.utils.Query;
import top.kwseeker.msa.user.dao.UserDao;
import top.kwseeker.msa.user.entity.UserEntity;
import top.kwseeker.msa.user.service.UserService;

import java.util.Map;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }
}