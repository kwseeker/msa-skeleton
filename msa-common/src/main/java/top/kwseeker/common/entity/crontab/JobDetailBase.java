package top.kwseeker.common.entity.crontab;

import java.util.Set;

public class JobDetailBase {

    protected JobBase jobBase;
    protected Set<TriggerBase> triggerBaseSet;

    public JobDetailBase() {}

    public JobDetailBase(JobBase jobBase, Set<TriggerBase> triggerBaseSet) {
        this.jobBase = jobBase;
        this.triggerBaseSet = triggerBaseSet;
    }

    public JobBase getJobBase() {
        return jobBase;
    }

    public void setJobBase(JobBase jobBase) {
        this.jobBase = jobBase;
    }

    public Set<TriggerBase> getTriggerBaseSet() {
        return triggerBaseSet;
    }

    public void setTriggerBaseSet(Set<TriggerBase> triggerBaseSet) {
        this.triggerBaseSet = triggerBaseSet;
    }

//    protected Class<? extends JobBase> jobBase;
//    protected Set<Class<? extends TriggerBase>> triggerBaseSet;
//
//
//    public Class<? extends JobBase> getJobBase() {
//        return jobBase;
//    }
//
//    public void setJobBase(Class<? extends JobBase> jobBase) {
//        this.jobBase = jobBase;
//    }
//
//    public Set<Class<? extends TriggerBase>> getTriggerBaseSet() {
//        return triggerBaseSet;
//    }
//
//    public void setTriggerBaseSet(Set<Class<? extends TriggerBase>> triggerBaseSet) {
//        this.triggerBaseSet = triggerBaseSet;
//    }
}
