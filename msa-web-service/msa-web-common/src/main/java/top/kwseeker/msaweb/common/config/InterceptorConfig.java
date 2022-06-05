//package top.kwseeker.msaweb.common.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import top.kwseeker.msaweb.common.interceptor.ResponseWrapInterceptor;
//
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new ResponseWrapInterceptor())
//                .addPathPatterns("/**");
//    }
//}