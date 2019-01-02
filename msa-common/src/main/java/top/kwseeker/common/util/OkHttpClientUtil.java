package top.kwseeker.common.util;

import com.google.common.collect.Multimap;
import okhttp3.*;
import okio.BufferedSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * OkHttp 的优势是连接同一IP和端口时，可以复用连接。
 * 避免每次重复建立连接，从而提升性能。
 *
 * TODO：拓展其他功能：
 *      认证处理
 *      取消Call执行
 *      拦截器监控，重写和重试Calls
 *      证书
 * 注意：
 *      Response使用完毕需要close()释放资源
 * 参考：
 *      https://m.2cto.com/kf/201606/521457.html
 */
public class OkHttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(OkHttpClientUtil.class);

    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_DELETE = "DELETE";

    private final static MediaType JSON = MediaType.parse("application/json;charset=utf-8");

    private static OkHttpClient okHttpClient;

    public static OkHttpClient getInstance() {
        if(okHttpClient == null) {
            synchronized (OkHttpClientUtil.class) {
                if(okHttpClient == null) {
                    logger.info("OkHttpClient 单例实例初始化");
                    okHttpClient = new OkHttpClient.Builder()
                            .retryOnConnectionFailure(true)
                            .connectTimeout(10, TimeUnit.SECONDS)   //也可以手动取消Call执行
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(30, TimeUnit.SECONDS)
                            //.cache(new Cache(cacheDirectory, cacheSize))  //配置响应缓存
                            .build();
                }
            }
        }
        return okHttpClient;
    }

    public static Request buildRequest(String url) {
        return buildRequest(url, METHOD_GET, null, null);
    }

    public static Request buildRequest(String url, String method, String requestBodyJsonStr) {
        RequestBody requestBody = RequestBody.create(JSON, requestBodyJsonStr);
        return buildRequest(url, method, null, requestBody);
    }

    /**
     * 构造请求（包括请求头，请求体，请求路径，请求方法，标签）
     *  请求头 一些键值对
     *  请求体 字符串、流、文件、表单参数、multipart
     *  请求路径 HttpUrl String URL
     *  请求方法 get post delete ...
     * @param url
     * @return
     */
    private static Request buildRequest(Object url,
                                        String methodType,
                                        Multimap headerMap,
                                        RequestBody requestBody
                                        ) throws NullPointerException {
        Request.Builder builder = new Request.Builder();

        if(url == null) {
            logger.error("OkHttp 请求路径不能为null");
            throw new NullPointerException("OkHttp 请求URL不能为null");
        }
        if(url instanceof String) {
            builder.url((String)url);
        } else if(url instanceof HttpUrl) {
            builder.url((HttpUrl) url);
        } else if(url instanceof URL) {
            builder.url((URL)url);
        }
        if(headerMap != null) {
            logger.info("This should not be print out, 功能尚未添加，后续完善");
        }
        if(methodType != null && !"".equals(methodType)) {
            methodType = methodType.toUpperCase();
            switch (methodType) {
                case METHOD_GET:
                    builder.get();
                    break;
                case METHOD_POST:
                    if(requestBody == null) {
                        logger.error("OkHttp 请求体不能为null");
                        throw new NullPointerException("OkHttp 请求体不能为null");
                    }
                    builder.post(requestBody);
                    break;
                default:
                    logger.error("暂不支持其他请求类型...");
            }
        }

        Request request = builder.build();
        logger.info("OkHttp 请求体: " + request.toString());
        return request;
    }

    public static Response syncRequest(String url) throws IOException {
        Request request = buildRequest(url);
        Response response = syncRequest(request);
        if (!response.isSuccessful()) {
            logger.info("OkHttp 应答异常: " + response.toString());
            throw new IOException("Unexpected code " + response);
        }
        return response;
    }

    /**
     * 同步请求通用方法
     */
    public static Response syncRequest(Request request) throws IOException {
        return okHttpClient.newCall(request).execute();
    }

    /**
     * 异步请求通用方法
     */
    public static void asyncRequest(Request request) {

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                logger.error("OkHttp 异步请求异常： e=" + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    logger.error("OkHttp 异步请求异常： e=" + response.toString());
                    throw new IOException("Unexpected code " + response);
                }

                //TODO: 异步返回处理
            }
        });
    }

}
