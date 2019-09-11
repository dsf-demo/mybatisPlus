package com.dsf.mp.annotation.dao;

import com.dsf.mp.annotation.AnnotationApp;
import com.dsf.mp.annotation.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AnnotationApp.class)
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void select() {
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setName("刘明强");
        user.setAge(31);
        user.setManagerId(2L);
//        user.setEmail("lmq@baomidou.com");
        user.setCreateTime(LocalDateTime.now());
        user.setRemark("我是一个备注哦~~");
        int row = userMapper.insert(user);
        System.out.println("影响记录数："+row);
        System.out.println("自增id: "+user.getId());
    }

}