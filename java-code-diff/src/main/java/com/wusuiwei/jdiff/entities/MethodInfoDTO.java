package com.wusuiwei.jdiff.entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MethodInfoDTO {
    /**
     * 方法的md5
     */
    public String md5;
    /**
     * 方法名
     */
    public String methodName;
    /**
     * 方法参数
     */
    public List<String> parameters;
}
