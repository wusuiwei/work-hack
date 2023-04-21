package com.wusuiwei.java.spring6.ioc.xml;

import lombok.Data;

@Data
public class User {
    private String username;
    private String age;

    public void run() {
        System.out.println("run...");
    }
}
