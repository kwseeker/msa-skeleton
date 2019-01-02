package top.kwseeker.common;

import java.util.Map;

/**
 * 请求与返回Json对象格式基类
 */
public class ReqEntity<T> {

    private ReqPubInfo pubInfo;
    private PageInfo pageInfo;
    private T busiInfo;    //业务请求参数, T：业务对象类型
    private Map<String,Object> extReqParam;


    public ReqPubInfo getPubInfo() {
        return pubInfo;
    }

    public void setPubInfo(ReqPubInfo pubInfo) {
        this.pubInfo = pubInfo;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
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
