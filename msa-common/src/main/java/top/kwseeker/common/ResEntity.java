package top.kwseeker.common;

import top.kwseeker.common.constant.ResCode;

import java.util.Date;
import java.util.Map;

public class ResEntity<T> {

    private ResPubInfo pubInfo;
    private PageInfo pageInfo;
    private T busiInfo;
    private Map<String, Object> extRepParam;

    public ResEntity() {}

    public ResEntity(ResPubInfo resPubInfo) {
        this.pubInfo = resPubInfo;
    }

    public ResEntity(ResPubInfo pubInfo, PageInfo pageInfo, T busiInfo, Map<String, Object> extRepParam) {
        this.pubInfo = pubInfo;
        this.pageInfo = pageInfo;
        this.busiInfo = busiInfo;
        this.extRepParam = extRepParam;
    }

    public static <T> ResEntity<T> responseSuccess() {
        return responseCode(ResCode.SUCCESS);
    }

    public static <T> ResEntity<T> responseError() {
        return responseCode(ResCode.OTHER_ERROR);
    }

    public static <T> ResEntity<T> responseCode(ResCode resCode) {
        ResPubInfo resPubInfo = new ResPubInfo();
        resPubInfo.setCode(resCode.getCode());
        resPubInfo.setMessage(resCode.getMsg());
        resPubInfo.setRspTime(new Date().toString());
        return new ResEntity<>(resPubInfo);
    }


    public ResPubInfo getPubInfo() {
        return pubInfo;
    }

    public void setPubInfo(ResPubInfo pubInfo) {
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
