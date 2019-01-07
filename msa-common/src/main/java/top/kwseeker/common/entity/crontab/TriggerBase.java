package top.kwseeker.common.entity.crontab;

public class TriggerBase {

    //触发器名字
    protected String name;
    //触发器分组，要和要触发的JobDetail同组
    protected String group;
    //Cron表达式
    protected String cronExpression;
    protected String description;

    public TriggerBase() {}

    public TriggerBase(String name, String group, String cronExpression, String description) {
        this.name = name;
        this.group = group;
        this.cronExpression = cronExpression;
        this.description = description;
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
