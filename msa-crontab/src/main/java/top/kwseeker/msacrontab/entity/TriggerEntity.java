package top.kwseeker.msacrontab.entity;

import com.google.common.base.Strings;
import org.quartz.*;
import top.kwseeker.common.entity.crontab.TriggerBase;

import java.text.ParseException;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 为指定的 JobDetail 创建 CronTrigger 触发器
 */
public class TriggerEntity extends TriggerBase {

    public TriggerEntity() {}

    public TriggerEntity(TriggerBase triggerBase) {
        this.name = triggerBase.getName();
        this.group = triggerBase.getGroup();
        this.cronExpression = triggerBase.getCronExpression();
        this.description = triggerBase.getDescription();
    }

    public CronTrigger convertToQuartzTrigger(JobDetail jobDetail) {
        CronExpression ce = null;
        try {
            checkArgument(!Strings.isNullOrEmpty(cronExpression), "cronExpression参数不能为null或空");
            ce = new CronExpression(this.cronExpression);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withSchedule(CronScheduleBuilder.cronSchedule(ce))
                .withIdentity(this.name, this.group)
                .withDescription(this.description)
                .build();
    }

}
