package top.kwseeker.common;

/**
 * 响应公共参数
 */
public class ResponsePubInfo {

    private String code;
    private String message;
    private String rsp_time;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRsp_time() {
        return rsp_time;
    }

    public void setRsp_time(String rsp_time) {
        this.rsp_time = rsp_time;
    }
}
