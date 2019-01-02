package top.kwseeker.msacrontab.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RpcJob implements Job {

    private final static Logger logger = LoggerFactory.getLogger(RpcJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("执行 RpcJob 任务...");
    }
}
