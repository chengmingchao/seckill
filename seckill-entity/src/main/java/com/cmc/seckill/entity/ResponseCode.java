package com.cmc.seckill.entity;

/**
 * 返回状态码
 */
public enum ResponseCode {
    SUCCESS(200,"请求成功"),
    ERROR(444,"请求失败"),
    PARAM_ERROR(445,"参数有误");

    private Integer code;
    private String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
