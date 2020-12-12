package com.cwquek.ecommerce.common.exception;

public enum BizCodeEnums {
    UKNOWN_EXCEPTION(1000, "Unknown System Error"),
    VALID_EXCEPTION(10001, "Validation Error");

    private int code;
    private String msg;

    BizCodeEnums(int code, String msg) {
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
