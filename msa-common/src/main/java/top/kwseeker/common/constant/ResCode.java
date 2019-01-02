package top.kwseeker.common.constant;

public enum ResCode {
    SUCCESS(0, "SUCCESS"),
    OTHER_ERROR(9999, "其他错误");

    private final int code;
    private final String msg;

    ResCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
