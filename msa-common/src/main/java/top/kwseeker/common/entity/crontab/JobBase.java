package top.kwseeker.common.entity.crontab;

import java.util.Map;

/**
 * 用于创建Job实体(JobDetail的实例)
 */
public class JobBase {

    //JobDetail名称
    protected String name;
    //JobDetail分组
    protected String group;
    //Job类名（必需，通过传参或者从extInfo解析）
    protected String targetClass;
    //JobDetail描述
    protected String description;
    //JobDetail拓展参数
    protected Map<String, Object> extInfo;

    public JobBase() {}

    public JobBase(String name, String group, String targetClass, String description, Map<String, Object> extInfo) {
        this.name = name;
        this.group = group;
        this.targetClass = targetClass;
        this.description = description;
        this.extInfo = extInfo;
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

    public String getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, Object> extInfo) {
        this.extInfo = extInfo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("JobEntity{");
        sb.append("name='").append(name).append('\'');
        sb.append(", group='").append(group).append('\'');
        sb.append(", targetClass='").append(targetClass).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", extInfo='").append(extInfo);
        sb.append('}');
        return sb.toString();
    }
}
