package top.kwseeker.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ApiModel
public class ResponseEntity<T> implements Serializable {

    @ApiModelProperty(value = "返回码", example = "200")
    private Integer code;
    @ApiModelProperty(value = "返回码描述", example = "ok")
    private String message;
    @ApiModelProperty(value = "响应时间戳", example = "1630826468012")
    private Long timestamp = System.currentTimeMillis();
    @ApiModelProperty(value = "返回结果")
    private T data;

    public ResponseEntity(ResultCode resultCode, T data) {
        new ResponseEntity<>(resultCode.getCode(), resultCode.getMessage(), data);
    }

    public ResponseEntity(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseEntity<T> responseSuccess(T responseData) {
        return new ResponseEntity<>(ResultCode.SUCCESS, responseData);
    }

    public static <T> ResponseEntity<T> responseSystemFailure() {
        return responseFailure(ResultCode.ERROR_SYSTEM);
    }

    public static <T> ResponseEntity<T> responseFailure(ResultCode resultCode) {
        return responseFailure(resultCode, null);
    }

    public static <T> ResponseEntity<T> responseFailure(ResultCode resultCode, T responseData) {
        return new ResponseEntity<>(resultCode, responseData);
    }
}
