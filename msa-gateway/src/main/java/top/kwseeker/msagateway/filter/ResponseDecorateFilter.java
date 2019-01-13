package top.kwseeker.msagateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * 使用Zuul Post过滤器可以对返回结果做修饰
 */
@Component
public class ResponseDecorateFilter extends ZuulFilter {

    private RequestContext requestContext = null;
    private HttpServletRequest request = null;
    private HttpServletResponse response = null;

    private static final Set<String> TOKEN_URIS = new HashSet<>();
    static {
        TOKEN_URIS.add("/msa-test/test/hello");
        TOKEN_URIS.add("/msatest/test/hello");
    }

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        requestContext = RequestContext.getCurrentContext();
        request = requestContext.getRequest();
        response = requestContext.getResponse();

        for(String url : TOKEN_URIS) {
            if(url.equals(request.getRequestURI())) {
                return true;
            }
        }

        return false;
    }

    public Object run() throws ZuulException {
        if(response != null) {
            //在返回头添加信息
            response.setHeader("X-Foo", UUID.randomUUID().toString());
        }

        return null;
    }
}
