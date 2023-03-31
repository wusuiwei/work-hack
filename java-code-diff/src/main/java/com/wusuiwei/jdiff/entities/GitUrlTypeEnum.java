package com.wusuiwei.jdiff.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Git Url 类型
 */
@Getter
@AllArgsConstructor
public enum GitUrlTypeEnum {
    HTTP(1, "http"),

    SSH(2, "ssh"),
    ;

    private Integer code;
    private String value;


}
