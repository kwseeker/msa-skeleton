package top.kwseeker.msacrontab.service.impl;

import com.google.common.collect.Lists;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import top.kwseeker.common.constant.ModuleTag;
import top.kwseeker.msacrontab.entity.JobDetailEntity;
import top.kwseeker.msacrontab.entity.JobEntity;
import top.kwseeker.msacrontab.entity.TriggerEntity;
import top.kwseeker.msacrontab.service.IQuartzJobDetailService;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class QuartzJobDetailService implements IQuartzJobDetailService {

    private static final Logger logger = LoggerFactory.getLogger(QuartzJobDetailService.class);

    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    /**
     * 添加定时任务
     */
    @Override
    public boolean addJob(JobDetailEntity jobDetailEntity) {
        JobDetail jobDetail = ((JobEntity)jobDetailEntity.getJobBase()).convertToQuartzJobDetail();
        Set<CronTrigger> triggerSet = jobDetailEntity.getTriggerBaseSet().stream().map(jtd ->
                ((TriggerEntity)jtd).convertToQuartzTrigger(jobDetail)
        ).collect(Collectors.toSet());

        try {
            scheduler.scheduleJob(jobDetail, triggerSet, true);
            return true;
        } catch (SchedulerException e) {
            logger.error(ModuleTag.CRONTAB_TAG + "QuartzJobDetailService addJob exception: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public JobDetail getJobDetailByKey(JobKey jobKey) {
        JobDetail jobDetail = null;
        try {
            jobDetail = scheduler.getJobDetail(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobDetail;
    }

    public List<Trigger> getTriggerByKey(JobKey jobKey) {
        List<Trigger> triggerList = Lists.newArrayList();
        try {
            triggerList = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return triggerList;
    }

    /**
     * 查看定时任务列表
     */
    @Override
    public List<JobDetailEntity> queryJobList() {
        List<JobDetailEntity> jobDetailEntities = Lists.newArrayList();

        //使用函数式编程的方式定义一个函数，通过JobKey获取对应的JobDetailEntity
        //输入 Set<JobKey> 返回 List<JobDetailBase>
        Function<Set<JobKey>, List<JobDetailEntity>> copyPropFun = jobKeys -> {
            List<JobDetailEntity> jobDetailEntityList = Lists.newArrayList();
            jobDetailEntityList = jobKeys.stream().map(jobKey -> {
                JobDetail jobDetail = null;
                List<Trigger> triggerList = this.getTriggerByKey(jobKey);
                jobDetail = this.getJobDetailByKey(jobKey);

                JobDetailEntity jobDetailEntity = new JobDetailEntity();
                jobDetailEntity.fillWithQuartzJobDetail.accept(jobDetail);
                jobDetailEntity.fillWithQuartzTriggers.accept(triggerList);
                return jobDetailEntity;
            }).collect(Collectors.toList());
            return jobDetailEntityList;
        };

        try {
            Set<JobKey> jobKeySet = scheduler.getJobKeys(GroupMatcher.anyJobGroup());
            jobDetailEntities = copyPropFun.apply(jobKeySet);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobDetailEntities;
    }

    /**
     * 通过jobKey查看定时任务
     */

    /**
     * 修改已有的定时任务
     */

    /**
     * 立即触发定时任务
     */

    /**
     * 暂停定时任务
     */

    /**
     * 恢复定时任务
     */

    /**
     * 删除定时任务
     */

}
