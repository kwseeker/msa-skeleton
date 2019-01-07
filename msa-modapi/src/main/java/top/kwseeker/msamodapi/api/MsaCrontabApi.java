package top.kwseeker.msamodapi.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.kwseeker.common.ReqEntity;
import top.kwseeker.common.ResEntity;
import top.kwseeker.common.entity.crontab.JobDetailBase;

//@FeignClient(name = "msa-crontab")        //这样写调用者模块扫描不到，妥协的方式是再每个调用者模块继承这个接口，并用@FeignClient注释
public interface MsaCrontabApi {

    @GetMapping("/jobs/list")
    ResEntity listJob();

    //TODO： MSA-CRONTAB 中类型为 ReqEntity<JobDetailEntity> 怎么也能匹配上？
    @PostMapping("/jobs/add")
    ResEntity addJob(@RequestBody ReqEntity<JobDetailBase> reqEntity);

}
