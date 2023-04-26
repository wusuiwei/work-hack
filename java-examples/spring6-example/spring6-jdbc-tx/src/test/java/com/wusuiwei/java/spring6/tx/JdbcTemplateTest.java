package com.wusuiwei.java.spring6.tx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Map;

/**
 * JdbcTemplate 增删改查操作
 */
@SpringJUnitConfig(locations = "classpath:bean.xml")
public class JdbcTemplateTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Test
    void test() {
        /*新增操作*/
//        String sql = "insert into t_emp (id, name, age, sex)   values (null,?,?,?)";
//        int insert = jdbcTemplate.update(sql, "jack", 20, "男");
//        System.out.println(insert);

        /*修改操作*/
//        String sql = "update t_emp set name=? where id=?";
//        int update = jdbcTemplate.update(sql, "李四",2);
//        System.out.println(update);

        /*删除操作*/
//        String sql = "delete from  t_emp  where id=?";
//        int delete = jdbcTemplate.update(sql, 2);
//        System.out.println(delete);

        /*查询操作*/
        //返回对象
//        String sql = "select * from t_emp where id =?";
//        //写法一：
////        Emp result = jdbcTemplate.queryForObject(sql, (res, rowNum) -> {
////            Emp emp = new Emp();
////            emp.setId(res.getInt("id"));
////            emp.setName(res.getString("name"));
////            emp.setAge(res.getInt("age"));
////            emp.setSex(res.getString("sex"));
////            return emp;
////        }, 2);
//        //写法二：
//        Emp result = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Emp.class), 2);
//        System.out.println(result);
        //返回list集合
//        String sql = "select * from t_emp";
//        List<Emp> emps = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Emp.class));
//        System.out.println(emps);

        //返回单个值
        String sql = "select count(*) from t_emp";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(result);

    }
}
