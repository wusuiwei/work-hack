package com.wusuiwei.jdiff.commons;

import jakarta.servlet.http.HttpServletResponse;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 165367809284687797L;

    private int code;

    /**
     * 错误消息
     */
    private String message;


    /**
     * @param exceptionEnum 异常枚举类型
     */
    public BusinessException(BusinessExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMsg();
    }

    /**
     * @param message 错误描述
     */
    public BusinessException(String message) {
        super(message);
        this.code = HttpServletResponse.SC_NO_CONTENT;
        this.message = message;
    }

    /**
     * @param code    错误码
     * @param message 错误描述
     */
    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * @param code
     * @param message
     * @param cause
     */
    public BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
