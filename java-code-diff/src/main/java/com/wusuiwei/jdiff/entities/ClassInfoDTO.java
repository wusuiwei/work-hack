package com.wusuiwei.jdiff.entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClassInfoDTO {
    /**
     * java文件
     */
    private String classFile;
    /**
     * 类名
     */
    private String className;
    /**
     * 包名
     */
    private String packages;


    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 类中的方法
     */
    private List<MethodInfoDTO> methodInfos;

    /**
     * 新增的行数
     */
    private List<int[]> addLines;

    /**
     * 删除的行数
     */
    private List<int[]> delLines;

    /**
     * 修改类型
     */
    private String type;


    /**
     * 变更行
     */
    private List<ChangeLine> lines;

}
