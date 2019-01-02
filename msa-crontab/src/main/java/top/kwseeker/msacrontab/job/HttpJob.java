package top.kwseeker.msacrontab.job;

import okhttp3.*;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.kwseeker.common.util.OkHttpClientUtil;

import java.io.IOException;
import java.util.Objects;

/**
 * Http请求 任务
 * 用于定时发送Http请求
 */
@Component
public class HttpJob implements Job {

    private final static Logger logger = LoggerFactory.getLogger(HttpJob.class);

    private final static MediaType JSON = MediaType.parse("application/json;charset=utf-8");

    //TODO: 此处为何注入失败，okHttpClient 总是为null
//    @Autowired
//    private OkHttpClient okHttpClient;

    //Job实体类构造使用的是默认的无参构造函数，这里不能使用构造注入
//    private final OkHttpClient okHttpClient;
//
//    @Autowired
//    public HttpJob(@Qualifier("okHttpClient") OkHttpClient okHttpClient) {
//        this.okHttpClient = okHttpClient;
//    }


    /**
     * HttpJob任务主体
     */
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
//        String uniqueKey = MessageFormat.format("{0}[{1}]", jobKey.getGroup(), jobKey.getName());
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String method = String.valueOf(jobDataMap.get("method"));
        String url = String.valueOf(jobDataMap.get("url"));
        String jsonStr = String.valueOf(jobDataMap.get("jsonParams"));

        Request request = OkHttpClientUtil.buildRequest(url, method, jsonStr);
        Response response = null;
        ResponseBody responseBody = null;
        String result = null;
        OkHttpClient okHttpClient = OkHttpClientUtil.getInstance();
        try {
            response = okHttpClient.newCall(request).execute();
            if(!response.isSuccessful()) {
                logger.error("请求响应失败：response=" + response.toString());
                return;
            }
            if((responseBody = response.body()) == null) {
                logger.error("请求响应体结果为null");
                return;
            }
            logger.debug("请求成功：response=" + response.toString());
            result = responseBody.string();
            logger.info("请求响应体：" + result);
        } catch (IOException e) {
            logger.info("OkHttp 请求异常：", e.getMessage());
            e.printStackTrace();
        } finally {
            logger.info("method:{} | url:{} | params:{} | resp:{}", method, url, jsonStr, result);
            if(Objects.nonNull(response)) {
                response.close();
            }
        }
    }
}
