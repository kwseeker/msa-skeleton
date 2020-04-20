package top.kwseeker.msagateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 令牌校验
 */
@Component
public class TokenValidateFilter extends ZuulFilter {

    private RequestContext requestContext = null;
    private HttpServletRequest request = null;

    private static final Set<String> TOKEN_URIS = new HashSet<>();
    static {
        TOKEN_URIS.add("/msa-test/test/hello");
        TOKEN_URIS.add("/msatest/test/hello");
    }

    //下面两个方法继承自ZuulFilter抽象方法
    //过滤器类型(这里和普通的过滤器不一样，普通过滤器请求及返回都会经过，而ZuulFilter是分开的)
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    //过滤器处于过滤器链的位置（数值越小优先级越高）
    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    //继承自IZuulFilter接口
    //此过滤器是否开启（如果需要设置只对某些接口有效逻辑可以在这里实现）
    @Override
    public boolean shouldFilter() {
        requestContext = RequestContext.getCurrentContext();
        request = requestContext.getRequest();

        for(String url : TOKEN_URIS) {
            if(url.equals(request.getRequestURI())) {
                return true;
            }
        }

        return false;
    }

    //过滤器过滤逻辑实现
    //TODO：这个返回值什么用 ZuulFilterResult
    public Object run() throws ZuulException {
        //从请求上下文获取请求
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        HttpServletRequest request = requestContext.getRequest();

        //获取请求中的Token，具体看放在请求的哪个部分
        //假设放在参数中
        String token = null;
        if(request != null && StringUtils.isEmpty(token = request.getParameter("token"))) {
            //直接返回错误码
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }

        //其他校验逻辑 ...

        return null;
    }
}
