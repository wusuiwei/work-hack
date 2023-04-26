package com.wusuiwei.java.spring6.tx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig("bean.xml")
public class JdbcTemplateTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Test
    void test() {

    }
}
