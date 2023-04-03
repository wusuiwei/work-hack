package com.wusuiwei.jdiff.entities;

import lombok.Data;

@Data
public class ChangeLineVO {
    /**
     * 变更类型
     */
    private String type;

    private Integer startLineNum;

    private Integer endLineNum;
}
