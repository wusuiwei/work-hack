package com.wusuiwei.java.spring6.ioc.xml.ditest;

import lombok.Data;

/**
 * 员工类
 */
@Data
public class Emp {

    private Dept dept;

    private String ename;
    private Integer age;
    private String[] loves;

    public void work() {
        System.out.println(ename + " emp work...." + age);
        dept.info();
    }
}
