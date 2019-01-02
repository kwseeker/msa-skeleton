package top.kwseeker.msacrontab.service;

import top.kwseeker.msacrontab.entity.JobDetailEntity;

import java.util.List;

public interface IQuartzJobDetailService {

    boolean addJob(JobDetailEntity jobDetailEntity);

    List<JobDetailEntity> queryJobList();

}
