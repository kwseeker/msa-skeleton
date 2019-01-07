package top.kwseeker.msacrontab.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.kwseeker.common.ReqEntity;
import top.kwseeker.common.ResEntity;
import top.kwseeker.common.ResPubInfo;
import top.kwseeker.common.constant.ResCode;
import top.kwseeker.common.entity.crontab.TriggerBase;
import top.kwseeker.msacrontab.entity.JobDetailEntity;
import top.kwseeker.msacrontab.entity.JobEntity;
import top.kwseeker.msacrontab.entity.TriggerEntity;
import top.kwseeker.msacrontab.service.IQuartzJobDetailService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Quartz 定时任务 Controller
 */
@RestController
@RequestMapping("/jobs")
public class QuartzJobController {

    private static final Logger logger = LoggerFactory.getLogger(QuartzJobController.class);

    @Autowired
    IQuartzJobDetailService quartzJobDetailService;

    /**
     * 添加定时任务
     */
    @PostMapping("/add")
    public ResEntity<Boolean> addJob(@RequestBody ReqEntity<JobDetailEntity> reqEntity) {
        logger.info("执行 QuartzJobController addJob ---> begin, param=" + reqEntity.toString());
        JobDetailEntity jobDetailEntity = reqEntity.getBusiInfo();

        //类型替换
        //TODO: 这段代码有点丑
        JobEntity jobEntity = new JobEntity(jobDetailEntity.getJobBase());
        jobDetailEntity.setJobBase(jobEntity);
        Set<TriggerBase> triggerBaseSet = jobDetailEntity.getTriggerBaseSet();
        Set<TriggerBase> triggerEntitySet = new HashSet<>();
        for(TriggerBase triggerBase : triggerBaseSet) {
            triggerEntitySet.add(new TriggerEntity(triggerBase));
        }
        jobDetailEntity.setTriggerBaseSet(triggerEntitySet);

        boolean result = quartzJobDetailService.addJob(jobDetailEntity);
        if(result) {
            logger.info("执行 QuartzJobController addJob ---> end");
            return ResEntity.responseSuccess();
        }
        logger.info("执行 QuartzJobController addJob ---> end");
        return ResEntity.responseError();
    }

    /**
     * 查看定时任务列表
     */
    @GetMapping("/list")
    public ResEntity<List<JobDetailEntity>> listJobs() {
        logger.info("执行 QuartzJobController listJobs ---> begin");
        List<JobDetailEntity> jobDetailEntityList = quartzJobDetailService.queryJobList();
        if(jobDetailEntityList != null) {
            logger.info("执行 QuartzJobController listJobs ---> end");
            return new ResEntity(new ResPubInfo(ResCode.SUCCESS), null, jobDetailEntityList,null);
        }
        logger.info("执行 QuartzJobController listJobs ---> end");
        return ResEntity.responseError();
    }

    /**
     * 通过jobKey查看定时任务
     */
//    @GetMapping("/{jobKey}")
//    public ResEntity<> getJobByKey() {
//
//    }


    /**
     * 修改已有的定时任务
     */
//    @PostMapping("/modify")
//    public ResEntity<> modifyJobByKey() {
//
//    }

    /**
     * 立即触发定时任务
     */
//    @PostMapping("/fire")
//    public ResEntity fireJob() {
//
//    }

    /**
     * 暂停定时任务
     */
//    @PostMapping("/pause")
//    public ResEntity pauseJob() {
//
//    }

    /**
     * 恢复定时任务
     */
//    @PostMapping("/resume")
//    public ResEntity resumeJob() {
//
//    }

    /**
     * 删除定时任务
     */
//    @PostMapping("/delete")
//    public ResEntity deleteJob() {
//
//    }

}
