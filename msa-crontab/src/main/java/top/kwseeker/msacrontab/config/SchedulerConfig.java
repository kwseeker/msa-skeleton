package top.kwseeker.msacrontab.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class SchedulerConfig {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerConfig.class);

    @PostConstruct
    public void quartzInit() {
        logger.info("Quartz initialize done ...");
    }

//    @Autowired
//    private QuartzScheduler quartzScheduler;

    /**
     * 初始启动quartz
     */
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        try {
//            quartzScheduler.startJob();
//            System.out.println("任务已经启动...");
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 初始注入scheduler
     */
    @Bean(name = "Scheduler")
    public Scheduler scheduler() throws IOException, SchedulerException {
        SchedulerFactory schedulerFactoryBean = new StdSchedulerFactory(quartzProperties());
        return schedulerFactoryBean.getScheduler();
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        //在quartz.properties中的属性被读取并注入后再初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    /*
     * quartz初始化监听器
     */
    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }
}
