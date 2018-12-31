package top.kwseeker.common;

import java.util.Map;

/**
 * 请求与返回Json对象格式基类
 */
public class RequestEntity<T> {

    private RequestPubInfo requestPubInfo;
    private PageInfo requestPageInfo;
    private T busiInfo;    //业务请求参数, T：业务对象类型
    private Map<String,Object> extReqParam;


    public RequestPubInfo getRequestPubInfo() {
        return requestPubInfo;
    }

    public void setRequestPubInfo(RequestPubInfo requestPubInfo) {
        this.requestPubInfo = requestPubInfo;
    }

    public PageInfo getRequestPageInfo() {
        return requestPageInfo;
    }

    public void setRequestPageInfo(PageInfo requestPageInfo) {
        this.requestPageInfo = requestPageInfo;
    }

    public T getBusiInfo() {
        return busiInfo;
    }

    public void setBusiInfo(T busiInfo) {
        this.busiInfo = busiInfo;
    }

    public Map<String, Object> getExtReqParam() {
        return extReqParam;
    }

    public void setExtReqParam(Map<String, Object> extReqParam) {
        this.extReqParam = extReqParam;
    }
}
