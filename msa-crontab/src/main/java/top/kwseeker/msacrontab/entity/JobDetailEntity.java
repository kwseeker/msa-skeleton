package top.kwseeker.msacrontab.entity;

import org.quartz.*;
import org.springframework.beans.BeanUtils;
import top.kwseeker.common.entity.crontab.JobDetailBase;
import top.kwseeker.common.entity.crontab.TriggerBase;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

//TODO: JobBase TriggerBase JobDetailBase => JobEntity TriggerEntity JobDetailEntity 有没有更好的实现方式
public class JobDetailEntity extends JobDetailBase {

    //定义一个从 JobDetail 到 JobDetailBase 的对应关系
    public transient Consumer<JobDetail> fillWithQuartzJobDetail = jd -> {
        JobEntity jobEntity = new JobEntity();
        JobKey jobKey = jd.getKey();

        BeanUtils.copyProperties(jobKey, jobEntity);    //
        jobEntity.setDescription(jd.getDescription());
        jobEntity.setTargetClass(jd.getJobClass().getCanonicalName());

        JobDataMap jdm = jd.getJobDataMap();
        if(Objects.nonNull(jdm)) {
            jobEntity.setExtInfo(jdm.getWrappedMap());
        }

        this.setJobBase(jobEntity);
    };

    public transient Consumer<List<Trigger>> fillWithQuartzTriggers = trList -> {
        Set<TriggerBase> triggerBases = trList.stream().map(tr -> {
            TriggerBase triggerBase = new TriggerBase();
            if(tr instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) tr;
                triggerBase.setCronExpression(cronTrigger.getCronExpression());
            }
            TriggerKey triggerKey = tr.getKey();
            triggerBase.setName(triggerKey.getName());
            triggerBase.setGroup(triggerKey.getGroup());
            triggerBase.setDescription(tr.getDescription());
            return triggerBase;
        }).collect(Collectors.toSet());
        this.setTriggerBaseSet(triggerBases);
    };
}
