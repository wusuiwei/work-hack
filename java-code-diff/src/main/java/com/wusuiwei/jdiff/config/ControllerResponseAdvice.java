package com.wusuiwei.jdiff.config;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.wusuiwei.jdiff.commons.BusinessException;
import com.wusuiwei.jdiff.commons.BusinessExceptionEnum;
import com.wusuiwei.jdiff.commons.ResultObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {
    /**
     * 业务异常处理
     *
     * @param e 业务异常
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResultObject exceptionHandler(BusinessException e) {
        e.printStackTrace();
        return ResultObject.failure(e.getCode(), e.getMessage());
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResultObject exceptionHandler(NullPointerException e) {
        e.printStackTrace();
        return ResultObject.failure(BusinessExceptionEnum.INTERNAL_SERVER_ERROR.getCode(),
                BusinessExceptionEnum.INTERNAL_SERVER_ERROR.getMsg());
    }

    /**
     * 未知异常处理
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultObject exceptionHandler(Exception e) {
        e.printStackTrace();
        return ResultObject.failure(BusinessExceptionEnum.UNKNOWN.getCode(),
                BusinessExceptionEnum.UNKNOWN.getMsg());
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // response是ResultObject类型，或者注释了NotControllerResponseAdvice注解都不进行包装
        return !returnType.getParameterType().isAssignableFrom(ResultObject.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // String类型不能直接包装
        if (body instanceof String) {
            return JSON.toJSONString(ResultObject.data(body, "操作成功"));
        }
        // 否则直接包装成ResultVo返回
        return ResultObject.data(body, "操作成功");
    }
}
