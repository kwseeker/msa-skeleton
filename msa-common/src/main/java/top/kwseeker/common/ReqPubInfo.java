package top.kwseeker.common;

import java.util.Map;

/**
 * 公共请求参数
 */
public class ReqPubInfo {
    //请求来源
    private String reqSrc;
    //请求时间
    private String reqTime;
    //拓展参数
    private Map<String, Object> extPubInfo;


    public String getReqSrc() {
        return reqSrc;
    }

    public void setReqSrc(String reqSrc) {
        this.reqSrc = reqSrc;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public Map<String, Object> getExtPubInfo() {
        return extPubInfo;
    }

    public void setExtPubInfo(Map<String, Object> extPubInfo) {
        this.extPubInfo = extPubInfo;
    }

//    private String reqSrc;
//    private String ver;
//    private String userId;
//    private String mt;
//    private String reqTime;
//    private String sign;
}
