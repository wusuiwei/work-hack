package com.wusuiwei.java.spring6.helloworld;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;

    public void add() {
        System.out.println("add...");
    }
}
