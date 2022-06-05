package top.kwseeker.msaweb.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
@Component("responseResultInterceptor")
public class ResponseWrapInterceptor implements HandlerInterceptor {

    public static final String RESPONSE_RESULT_ANNOTATION = "RESPONSE-RESULT-ANNO";

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            if (clazz.isAnnotationPresent(ResponseWrap.class)) {
                request.setAttribute(RESPONSE_RESULT_ANNOTATION, clazz.getAnnotation(ResponseWrap.class));
            } else if (method.isAnnotationPresent(ResponseWrap.class)) {
                request.setAttribute(RESPONSE_RESULT_ANNOTATION, method.getAnnotation(ResponseWrap.class));
            }
        }
        return true;
    }
}
