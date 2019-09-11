package com.dsf.mp.fakeDelete.dao;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dsf.mp.fakeDelete.FakeDeleteApp;
import com.dsf.mp.fakeDelete.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.format.DateTimeFormatter;
import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FakeDeleteApp.class)
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
    public void deleteById(){
        int rows = userMapper.deleteById(1171357729945505793L);
        System.out.println("影响行数："+rows);
    }

    @Test
    public void selectById(){
        User user = userMapper.selectById(1171357729945505793L);
        System.out.println(user);
    }

    @Test
    public void updateById(){
        User user = new User();
        user.setAge(32);
        user.setId(1171357729945505793L);
        int rows = userMapper.updateById(user);
        System.out.println("影响行数："+rows);
    }

    @Test
    public void mySelectList(){
        List<User> list = userMapper.mySelectList(
            Wrappers.<User>lambdaQuery()
//                .select(User.class,x->!x.getColumn().equals("deleted"))   //方式1
                .select(User.class,x->!x.isLogicDelete())   //方式2
                .gt(User::getAge, 25)
                .eq(User::getDeleted,0)
        );
        list.forEach(System.out::println);
    }


}