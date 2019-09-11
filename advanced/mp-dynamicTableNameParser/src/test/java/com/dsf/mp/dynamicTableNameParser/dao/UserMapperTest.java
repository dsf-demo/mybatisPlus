package com.dsf.mp.dynamicTableNameParser.dao;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dsf.mp.dynamicTableNameParser.DynamicTableNameParserApp;
import com.dsf.mp.dynamicTableNameParser.configuration.MybatisPlusConf;
import com.dsf.mp.dynamicTableNameParser.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicTableNameParserApp.class)
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    /**
     * 测试动态表名查询方法
     */
    @Test
    public void selectList(){
        MybatisPlusConf.myTableName.set("user_2019");
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    /**
     * 测试动态表名更新方法
     */
    @Test
    public void updateById(){
        MybatisPlusConf.myTableName.set("user_2019");
        User user = userMapper.selectById(1094592041087729666L);
        user.setAge(34);
        int rows = userMapper.updateById(user);
        System.out.println("受影响的行数："+rows);
    }

    /**
     * 测试动态表名插入方法
     */
    @Test
    public void insert(){
        MybatisPlusConf.myTableName.set("user_2019");
        User user = new User();
        user.setName("李国民");
        user.setAge(29);
        user.setEmail("lgm@baomidou.com");
        int rows = userMapper.insert(user);
        System.out.println("受影响的行数："+rows);
    }


    /**
     * 测试过滤掉内置方法的动态表名
     */
    @Test
    public void selectById(){
        MybatisPlusConf.myTableName.set("user_2019");
        User user = userMapper.selectById(1094590409767661570L);
        System.out.println(user);
    }

    /**
     * 测试过滤掉自定义方法的动态表名
     */
    @Test
    public void mySelectList(){
        MybatisPlusConf.myTableName.set("user_2019");
        List<User> list = userMapper.mySelectList(
            Wrappers.<User>lambdaQuery().gt(User::getAge,30)
        );
        list.forEach(System.out::println);
    }
}