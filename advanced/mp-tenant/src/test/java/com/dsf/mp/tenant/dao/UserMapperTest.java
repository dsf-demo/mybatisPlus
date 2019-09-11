package com.dsf.mp.tenant.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dsf.mp.tenant.TenantApp;
import com.dsf.mp.tenant.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TenantApp.class)
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    /**
     * 测试多租户查询方法
     * 效果：where ... and manager_id=1088248166370832385
     */
    @Test
    public void selectList(){
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    /**
     * 测试多租户更新方法
     */
    @Test
    public void updateById(){
        User user = userMapper.selectById(1094592041087729666L);
        user.setAge(34);
        int rows = userMapper.updateById(user);
        System.out.println("受影响的行数："+rows);
    }

    /**
     * 测试多租户插入方法
     */
    @Test
    public void insert(){
        User user = new User();
        user.setName("李国民");
        user.setAge(29);
        user.setEmail("lgm@baomidou.com");
        int rows = userMapper.insert(user);
        System.out.println("受影响的行数："+rows);
    }


    /**
     * 测试过滤掉内置方法的多租户功能
     */
    @Test
    public void selectById(){
        User user = userMapper.selectById(1094590409767661570L);
        System.out.println(user);
    }

    /**
     * 测试过滤掉自定义方法的多租户功能
     */
    @Test
    public void mySelectList(){
        List<User> list = userMapper.mySelectList(
            Wrappers.<User>lambdaQuery().gt(User::getAge,30)
        );
        list.forEach(System.out::println);
    }
}