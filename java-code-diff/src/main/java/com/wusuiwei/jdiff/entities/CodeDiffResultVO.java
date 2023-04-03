package com.wusuiwei.jdiff.entities;

import lombok.Data;

import java.util.List;

@Data
public class CodeDiffResultVO {
    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * java文件
     */
    private String classFile;

    /**
     * 类中的方法
     */
    private List<MethodInfoResultVO> methodInfos;


    /**
     * 修改类型
     */
    private String type;

    /**
     * 变更行
     */
    private List<ChangeLineVO> lines;
}
