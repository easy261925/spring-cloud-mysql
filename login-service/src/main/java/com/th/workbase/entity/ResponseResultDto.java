package com.th.workbase.entity;

import com.th.workbase.common.commonEnum.ErrorEnum;
import com.th.workbase.common.commonEnum.SuccessEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2021-04-16-15:17
 * @Author tangJ
 * @Description
 * @Version 1.0
 */
@Data
public class ResponseResultDto {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码",example = "1")
    private Integer code;

    @ApiModelProperty(value = "返回信息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();

    private ResponseResultDto() {
    }

    public static ResponseResultDto ok() {
        ResponseResultDto responseResult = new ResponseResultDto();
        responseResult.setSuccess(true);
        responseResult.setCode(SuccessEnum.S_200.getSuccessCode());
        responseResult.setMessage(SuccessEnum.S_200.getSuccessMsg());
        return responseResult;
    }

    public static ResponseResultDto error() {
        ResponseResultDto responseResult = new ResponseResultDto();
        responseResult.setSuccess(false);
        responseResult.setCode(ErrorEnum.E_500.getErrorCode());
        responseResult.setMessage(ErrorEnum.E_500.getErrorMsg());
        return responseResult;
    }

    public static ResponseResultDto loginError() {
        ResponseResultDto responseResult = new ResponseResultDto();
        responseResult.setSuccess(false);
        responseResult.setCode(ErrorEnum.E_401.getErrorCode());
        responseResult.setMessage(ErrorEnum.E_401.getErrorMsg());
        return responseResult;
    }

    public static ResponseResultDto ServiceError(String message) {
        ResponseResultDto responseResult = new ResponseResultDto();
        responseResult.setSuccess(false);
        responseResult.setCode(ErrorEnum.E_422.getErrorCode());
        responseResult.setMessage(message);
        return responseResult;
    }

    public ResponseResultDto success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public ResponseResultDto message(String message) {
        this.setMessage(message);
        return this;
    }

    public ResponseResultDto code(Integer code) {
        this.setCode(code);
        return this;
    }

    public ResponseResultDto data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public ResponseResultDto data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
