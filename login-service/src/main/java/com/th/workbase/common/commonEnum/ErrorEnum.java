package com.th.workbase.common.commonEnum;

/**
 * @Date 2021-04-16-15:21
 * @Author tangJ
 * @Description
 * @Version 1.0
 */
public enum ErrorEnum {
    /*
     * 错误信息
     * */
    E_401(401, "未授权"),
    E_422(422, "验证错误"),
    E_500(500, "服务器内部错误 - 服务器发生错误，请检查服务器"),
    ;

    private Integer errorCode;

    private String errorMsg;

    ErrorEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
