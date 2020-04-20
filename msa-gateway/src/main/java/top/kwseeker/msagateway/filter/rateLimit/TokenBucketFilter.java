package top.kwseeker.msagateway.filter.rateLimit;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * 令牌桶算法
 *
 * Google 的 guava 中有令牌桶的官方实现
 */
@Component
public class TokenBucketFilter extends ZuulFilter {

    //TODO: netflix discovery 中也有 RateLimiter 工具类，和 Guava RateLimiter 区别？
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);    //每秒最多100个请求

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    public Object run() throws ZuulException {
        if(!RATE_LIMITER.tryAcquire()) {
            throw new ZuulException("未成功从令牌桶获取令牌", HttpStatus.BAD_GATEWAY.value(), "令牌桶令牌不足");
        }
        return null;
    }
}
