package com.wusuiwei.jdiff.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangeLine {
    /**
     * 变更类型
     */
    private String type;

    private Integer startLineNum;

    private Integer endLineNum;

}
