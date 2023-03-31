package com.wusuiwei.jdiff.commons;

/**
 * 业务异常枚举类
 */
public enum BusinessExceptionEnum {

    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    // 未知异常
    UNKNOWN(10000, "未知异常!"),
    // 自定义
    TYPE_WRAPPING_EXCEPTION(10001, "类型包装异常"),
    GIT_AUTH_FAILED(20000, "Git授权失败"),
    GIT_OPERATED_FAIlED(20001, "Git 拉取代码失败"),
    PARSE_BRANCH_ERROR(20002, "Git 解析分支失败"),
    UNKNOWN_REPOSITY_URL(20003, "未知类型仓库地址"),
    GET_DIFF_CLASS_ERROR(20004, "分支比对失败"),
    PARSE_JAVA_FILE(30000, "格式化Java文件失败"),
    ;

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
