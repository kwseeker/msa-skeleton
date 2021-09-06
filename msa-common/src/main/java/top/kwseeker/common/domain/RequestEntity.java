package top.kwseeker.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@ApiModel
public class RequestEntity<T> implements Serializable {

    @ApiModelProperty(value = "请求来源", example = "h5")
    private String source;
    @ApiModelProperty(value = "请求时间", example = "1630826468012")
    private Long timestamp;
    @ApiModelProperty(value = "业务请求参数")
    private T data;
    @ApiModelProperty(value = "拓展请求参数")
    private Map<String, Object> ext;
}
