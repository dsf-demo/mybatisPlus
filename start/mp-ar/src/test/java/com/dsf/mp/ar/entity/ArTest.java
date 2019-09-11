package com.dsf.mp.ar.entity;

import com.dsf.mp.ar.ArApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * AR模式
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= ArApp.class)
public class ArTest {

    @Test
    public void insert(){
        User user = new User();
        user.setName("刘花");
        user.setAge(31);
        user.setManagerId(1088248166370832385L);
//        user.setEmail("lh@baomidou.com");
        user.setCreateTime(LocalDateTime.now());
        boolean success = user.insert();
        System.out.println("是否插入成功："+success);
    }

    @Test
    public void selectById(){
        User user = new User();
        User userSelect = user.selectById(1088248166370832385L);
        System.out.println("返回的是同一个User对象吗："+(userSelect==user));
        System.out.println(userSelect);
    }

    @Test
    public void selectById2(){
        User user = new User();
        user.setId(1088248166370832385L);
        User userSelect = user.selectById();
        System.out.println("返回的是同一个User对象吗："+(userSelect==user));
        System.out.println(userSelect);
    }

    @Test
    public void updateById(){
        User user = new User();
        user.setId(1170760467657715713L);
        user.setName("张草草");
        boolean success = user.updateById();
        System.out.println("是否更新成功："+success);
    }

    /**
     * 删除：
     * 如果没有指定的id，受影响的行数是0，也视为删除成功
     */
    @Test
    public void deleteById(){
        User user = new User();
        user.setId(1170760467657715766L);
        boolean success = user.deleteById();
        System.out.println("是否删除成功："+success);
    }

    /**
     * 添加或更新：
     * 如果没有设置id，则直接视为insert
     */
    @Test
    public void insertOrUpdate(){
        User user = new User();
        user.setName("张强");
        user.setAge(24);
        user.setEmail("zq@baomidou.com");
        user.setCreateTime(LocalDateTime.now());
        boolean success = user.insertOrUpdate();
        System.out.println("是否添加或更新成功（添加）："+success);
    }

    /**
     * 添加或更新：
     * 如果设置id，则先查询是否有此id的记录，如果有此id记录，则视为update,如果没有则视为insert
     */
    @Test
    public void insertOrUpdate2(){
        User user = new User();
        user.setId(1170767968797159426L);
        user.setAge(25);
        user.setCreateTime(LocalDateTime.now());
        boolean success = user.insertOrUpdate();
        System.out.println("是否添加或更新成功（更新）："+success);
    }
}