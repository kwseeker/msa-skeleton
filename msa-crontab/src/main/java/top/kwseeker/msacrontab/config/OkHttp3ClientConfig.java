package top.kwseeker.msacrontab.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * OkHttp3Client 配置
 */
@Configuration
public class OkHttp3ClientConfig {

    @Bean(name = "okHttpClient")
    public OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectionPool(pool())             //缺省也带有连接池
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build();
    }

    @Bean
    public ConnectionPool pool() {
        return new ConnectionPool(200, 5, TimeUnit.MINUTES);
    }
}
