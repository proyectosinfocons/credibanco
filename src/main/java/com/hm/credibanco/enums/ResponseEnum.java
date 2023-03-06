package com.hm.credibanco.enums;

public enum ResponseEnum {

    SUCCESS("SUCCESS", "00"),
    DELETE_SUCCESS("card has been removed", "01");

    private final String msg;
    private final String code;

    ResponseEnum(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }
}
