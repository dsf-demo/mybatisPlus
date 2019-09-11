package com.dsf.mp.sqlInjector.dao;

import com.dsf.mp.sqlInjector.SqlInjectorApp;
import com.dsf.mp.sqlInjector.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SqlInjectorApp.class)
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    /**
     * 测试自定义方法组件：删除全部
     */
    @Test
    public void deleteAll(){
        int rows = userMapper.deleteAll();
        System.out.println("受影响的行数："+rows);
    }

    /**
     * 测试选装件：批量删除
     * 如果插入时字段为null，则不管数据库是否设置默认值，插入的数据均是null
     */
    @Test
    public void insertBatchSomeColumn(){
        User user1 = new User();
        user1.setName("李兴华");
        user1.setAge(34);
        user1.setManagerId(1088248166370832385L);

        User user2 = new User();
        user2.setName("杨红");
        user2.setAge(29);
        user2.setManagerId(1088248166370832385L);

        List<User> asList= Arrays.asList(user1,user2);
        int rows = userMapper.insertBatchSomeColumn(asList);
        System.out.println("受影响的行数"+rows);

    }

    /**
     * 测试选装件：逻辑删除并填充
     */
    @Test
    public void deleteByIdWithFill(){
        User user = userMapper.selectById(1171846939462836226L);
//        user.setAge(23);

        int rows = userMapper.deleteByIdWithFill(user);

        System.out.println("受影响的行数"+rows);
    }

    /**
     * 测试选装件：根据id更新固定的字段
     */
    @Test
    public void alwaysUpdateSomeColumnById(){
        User user = userMapper.selectById(1088248166370832385L);
        user.setAge(26);
        user.setName("王地缝");

        int rows = userMapper.alwaysUpdateSomeColumnById(user);

        System.out.println("受影响的行数"+rows);
    }

}