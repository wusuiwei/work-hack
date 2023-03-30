package com.wusuiwei.jdiff.commons;

/**
 * 业务异常枚举类
 */
public enum BusinessExceptionEnum {

    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    // 未知异常
    UNKNOWN(10000, "未知异常!"),
    // 自定义
    TYPE_WRAPPING_EXCEPTION(10001, "类型包装异常");

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误描述
     */
    private String msg;

    BusinessExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
