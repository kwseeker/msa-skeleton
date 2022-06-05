package top.kwseeker.msaweb.user.config;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.kwseeker.msaweb.common.interceptor.ResponseWrapInterceptor;
//import top.kwseeker.msaweb.user.interceptor.ResponseResultInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    //@Autowired
    //private ResponseResultInterceptor responseResultInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ResponseWrapInterceptor())
                .addPathPatterns("/**");
    }
}