package top.kwseeker.msacrontab.entity;

import org.quartz.*;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class JobDetailEntity {

    private JobEntity jobEntity;
    private Set<TriggerEntity> triggerEntitySet;

    //定义一个从 JobDetail 到 JobDetailEntity 的对应关系
    public transient Consumer<JobDetail> fillWithQuartzJobDetail = jd -> {
        jobEntity = new JobEntity();
        JobKey jobKey = jd.getKey();

        BeanUtils.copyProperties(jobKey, jobEntity);    //
        jobEntity.setDescription(jd.getDescription());
        jobEntity.setTargetClass(jd.getJobClass().getCanonicalName());

        JobDataMap jdm = jd.getJobDataMap();
        if(Objects.nonNull(jdm)) {
            jobEntity.setExtInfo(jdm.getWrappedMap());
        }

        this.setJobEntity(jobEntity);
    };


    public transient Consumer<List<Trigger>> fillWithQuartzTriggers = trList -> {
        Set<TriggerEntity> triggerEntities = trList.stream().map(tr -> {
            TriggerEntity triggerEntity = new TriggerEntity();
            if(tr instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) tr;
                triggerEntity.setCronExpression(cronTrigger.getCronExpression());
            }
            TriggerKey triggerKey = tr.getKey();
            triggerEntity.setName(triggerKey.getName());
            triggerEntity.setGroup(triggerKey.getGroup());
            triggerEntity.setDescription(tr.getDescription());
            return triggerEntity;
        }).collect(Collectors.toSet());
        this.setTriggerEntitySet(triggerEntities);
    };


    public JobEntity getJobEntity() {
        return jobEntity;
    }

    public void setJobEntity(JobEntity jobEntity) {
        this.jobEntity = jobEntity;
    }

    public Set<TriggerEntity> getTriggerEntitySet() {
        return triggerEntitySet;
    }

    public void setTriggerEntitySet(Set<TriggerEntity> triggerEntitySet) {
        this.triggerEntitySet = triggerEntitySet;
    }
}
