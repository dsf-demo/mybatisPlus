package com.dsf.mp.start.dao;

import com.dsf.mp.start.StartApp;
import com.dsf.mp.start.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes= StartApp.class)
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void select(){
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

}