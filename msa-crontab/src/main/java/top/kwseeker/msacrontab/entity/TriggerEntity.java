package top.kwseeker.msacrontab.entity;

import com.google.common.base.Strings;
import org.quartz.*;

import java.text.ParseException;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 为指定的 JobDetail 创建 CronTrigger 触发器
 */
public class TriggerEntity {

    //触发器名字
    private String name;
    //触发器分组，要和要触发的JobDetail同组
    private String group;
    //Cron表达式
    private String cronExpression;
    private String description;

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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TriggerEntity{" +
                "name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
