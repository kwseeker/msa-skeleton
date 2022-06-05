//package top.kwseeker.msaweb.user.interceptor;
//
//import com.alibaba.fastjson.JSONObject;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//import top.kwseeker.common.domain.ResponseEntity;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * 将返回值包装成统一的格式返回
// */
//@Slf4j
//@ControllerAdvice
//@SuppressWarnings("NullableProblems")
//public class ResponseResultHandler implements ResponseBodyAdvice<Object> {
//
//    public static final String RESPONSE_RESULT_ANNOTATION = "RESPONSE-RESULT-ANNO";
//
//    @Override
//    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        if (attributes == null) {
//            return false;
//        }
//        HttpServletRequest request = attributes.getRequest();
//        ResponseResult responseResultAnno = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANNOTATION);
//        return responseResultAnno != null;
//    }
//
//    @Override
//    public Object beforeBodyWrite(Object body,
//                                  MethodParameter returnType,
//                                  MediaType selectedContentType,
//                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
//                                  ServerHttpRequest serverHttpRequest,
//                                  ServerHttpResponse serverHttpResponse) {
//        log.debug("统一格式返回处理: body=" + JSONObject.toJSONString(body));
//        Object ret = ResponseEntity.responseSuccess(body);
//        return ret;
//    }
//}
//
