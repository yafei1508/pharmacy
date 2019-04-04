package com.miaosha.error;

public enum EmBusinessError implements CommonError {
    //通用错误类型
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),

    //未知错误
    UNKNOWN_ERROR(30001, "未知错误"),
    STOCK_ERROR(40001,"库存操作错误"),


    USER_NOTE_EXIST(20001,"用户不存在"),
    USER_LOGIN_FAIL(20002,"用户名不存在或密码错误"),
    ;

    EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
    public int errCode;
    public String errMsg;
    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
