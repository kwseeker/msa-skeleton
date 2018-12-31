package top.kwseeker.common;

import java.util.Map;

public class ResponseEntity<T> {

    private ResponsePubInfo responsePubInfo;
    private PageInfo responsePageInfo;
    private T busiInfo;
    private Map<String, Object> extRepParam;


    public ResponsePubInfo getResponsePubInfo() {
        return responsePubInfo;
    }

    public void setResponsePubInfo(ResponsePubInfo responsePubInfo) {
        this.responsePubInfo = responsePubInfo;
    }

    public PageInfo getResponsePageInfo() {
        return responsePageInfo;
    }

    public void setResponsePageInfo(PageInfo responsePageInfo) {
        this.responsePageInfo = responsePageInfo;
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
