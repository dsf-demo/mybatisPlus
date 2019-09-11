package com.dsf.mp.autoFill.dao;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dsf.mp.autoFill.AutoFillApp;
import com.dsf.mp.autoFill.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.format.DateTimeFormatter;
import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AutoFillApp.class)
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setName("刘明强");
        user.setAge(31);
        user.setManagerId(1088248166370832385L);
        user.setEmail("lmq@baomidou.com");
        int row = userMapper.insert(user);
        System.out.println("影响记录数："+row);
    }

    @Test
    public void updateById(){
        User user = new User();
        user.setAge(32);
        user.setId(1171357729945505793L);
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        user.setUpdateTime(LocalDateTime.parse("2018-08-08 12:34:45",df));
        int rows = userMapper.updateById(user);
        System.out.println("影响行数："+rows);
    }

}