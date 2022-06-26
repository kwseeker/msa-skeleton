package top.kwseeker.msa.seata.aop;

import org.springframework.stereotype.Service;

@Service
public class DbMapperImpl implements DbMapper {

    @MyGlobalTransactional
    @Override
    public void insert() {
        System.out.println("执行插入sql...");
    }
}
