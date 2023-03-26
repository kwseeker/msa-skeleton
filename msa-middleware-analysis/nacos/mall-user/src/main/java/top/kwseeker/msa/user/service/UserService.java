package top.kwseeker.msa.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.kwseeker.msa.common.utils.PageUtils;
import top.kwseeker.msa.user.entity.UserEntity;

import java.util.Map;

public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
