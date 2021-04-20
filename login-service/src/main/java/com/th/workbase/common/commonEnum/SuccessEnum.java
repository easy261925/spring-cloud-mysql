package com.th.workbase.common.commonEnum;

/**
 * @Date 2021-04-16-15:19
 * @Author tangJ
 * @Description
 * @Version 1.0
 */
public enum SuccessEnum {
    /*
     * 成功信息
     * */
    S_200(200, "访问成功");

    private Integer successCode;

    private String successMsg;

    SuccessEnum(Integer successCode, String successMsg) {
        this.successCode = successCode;
        this.successMsg = successMsg;
    }

    public Integer getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(Integer successCode) {
        this.successCode = successCode;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }
}
