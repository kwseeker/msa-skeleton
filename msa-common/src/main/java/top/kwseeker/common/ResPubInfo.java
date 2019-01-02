package top.kwseeker.common;

import top.kwseeker.common.constant.ResCode;

/**
 * 响应公共参数
 */
public class ResPubInfo {

    private int code;
    private String message;
    private String rspTime;

    public ResPubInfo() {}

    public ResPubInfo(ResCode resCode) {
        this.code = resCode.getCode();
        this.message = resCode.getMsg();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRspTime() {
        return rspTime;
    }

    public void setRspTime(String rspTime) {
        this.rspTime = rspTime;
    }
}
