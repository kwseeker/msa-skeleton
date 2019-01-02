package top.kwseeker.msacrontab.entity;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.core.jmx.JobDataMapSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;
import top.kwseeker.msacrontab.constants.AppConst;
import top.kwseeker.msacrontab.job.HttpJob;
import top.kwseeker.msacrontab.job.RpcJob;

import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 用于创建Job实体(JobDetail的实例)
 *
 * 这里只是将创建做成中的比较散乱的配置封装到了一个类中
 *      将JobDetail 名称，所属组，描述，目标Job类型， 以及一些拓展信息，通过这个类进行指定；
 *      然后通过 convertToQuartzJobDetail() 构造JobDetail 实例
 */
public class JobEntity {

    private final static Logger logger = LoggerFactory.getLogger(JobEntity.class);

    private final static Map<String, Class<? extends Job>> SUPPORTED_JOB_TYPES = new HashMap<String, Class<? extends Job>>() {
        {
            put(AppConst.JobType.HTTP_JOB, HttpJob.class);
            put(AppConst.JobType.RPC_JOB, RpcJob.class);
        }
    };
    private final static Set<String> SUPPORT_EXT_FIELDS = new HashSet<String>() {
        {
            add("type");
            add("method");
            add("url");
            add("iface");
            add("jsonParam");
        }
    };

    //JobDetail名称
    private String name;
    //JobDetail分组
    private String group;
    //Job类名（必需，通过传参或者从extInfo解析）
    private String targetClass;
    //JobDetail描述
    private String description;
    //JobDetail拓展参数
    private Map<String, Object> extInfo;

    /**
     * 创建 JobDetail 实体
     * @return
     */
    public JobDetail convertToQuartzJobDetail() {
        Class<? extends Job> clazz = null;

        if(Objects.isNull(this.targetClass)) {
            String type = String.valueOf(this.extInfo.get("type"));
            clazz = SUPPORTED_JOB_TYPES.get(type);
            checkNotNull(clazz, "未匹配到type类型的Job");
            this.targetClass = clazz.getCanonicalName();
        }

        //加载类
        clazz = (Class<Job>) ClassUtils.resolveClassName(this.targetClass, this.getClass().getClassLoader());

        return JobBuilder.newJob()
                .ofType(clazz)
                .withIdentity(this.name, this.getGroup())
                .withDescription(this.description)
                .setJobData(JobDataMapSupport.newJobDataMap(this.extInfo))
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
