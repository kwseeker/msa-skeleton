package top.kwseeker.common.domain;

/**
 * HTTP/1.1 status code:
 *      https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html
 */
public enum ResultCode {
    //成功状态码
    SUCCESS(200, "成功"),
    //系统错误码
    ERROR_SYSTEM(500, "系统繁忙，请稍后重试"),
    //参数错误码 1000-9999

    //业务错误码 10000-99999

    ;

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
