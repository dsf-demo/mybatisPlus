package com.dsf.mp.crud.dao;

import com.dsf.mp.crud.CrudApp;
import com.dsf.mp.crud.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrudApp.class)
public class InsertTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setName("刘明强");
        user.setAge(31);
        user.setManagerId(1088248166370832385L);
//        user.setEmail("lmq@baomidou.com");
        user.setCreateTime(LocalDateTime.now());
        int row = userMapper.insert(user);
        System.out.println("影响记录数："+row);
        System.out.println("自增id: "+user.getId());
    }

}