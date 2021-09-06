package top.kwseeker.common;

import top.kwseeker.common.constant.ResCode;

import java.util.Date;
import java.util.Map;

public class RespEntity<T> {

    private RespPubInfo pubInfo;
    private PageInfo pageInfo;
    private T busiInfo;
    private Map<String, Object> extRepParam;

    public RespEntity() {}

    public RespEntity(RespPubInfo respPubInfo) {
        this.pubInfo = respPubInfo;
    }

    public RespEntity(RespPubInfo pubInfo, PageInfo pageInfo, T busiInfo, Map<String, Object> extRepParam) {
        this.pubInfo = pubInfo;
        this.pageInfo = pageInfo;
        this.busiInfo = busiInfo;
        this.extRepParam = extRepParam;
    }

    public static <T> RespEntity<T> responseSuccess() {
        return responseCode(ResCode.SUCCESS);
    }

    public static <T> RespEntity<T> responseError() {
        return responseCode(ResCode.OTHER_ERROR);
    }

    public static <T> RespEntity<T> responseCode(ResCode resCode) {
        RespPubInfo respPubInfo = new RespPubInfo();
        respPubInfo.setCode(resCode.getCode());
        respPubInfo.setMessage(resCode.getMsg());
        respPubInfo.setRspTime(new Date().toString());
        return new RespEntity<>(respPubInfo);
    }


    public RespPubInfo getPubInfo() {
        return pubInfo;
    }

    public void setPubInfo(RespPubInfo pubInfo) {
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

    public Map<String, Object> getExtRepParam() {
        return extRepParam;
    }

    public void setExtRepParam(Map<String, Object> extRepParam) {
        this.extRepParam = extRepParam;
    }
}
