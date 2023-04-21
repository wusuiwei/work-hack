package com.wusuiwei.java.spring6.ioc.xml.ditest;

import lombok.Data;

import java.util.List;

/**
 * 部门类
 */
@Data
public class Dept {

    private String danme;

    private List<Emp> empList;
    public void info() {
        System.out.println("部门名称"+danme);
    }
}
