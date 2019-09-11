package com.dsf.mp.crud.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dsf.mp.crud.CrudApp;
import com.dsf.mp.crud.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrudApp.class)
public class DeleteTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void deleteById() {
        int rows = userMapper.deleteById(1170243901535006722L);

        System.out.println("影响记录数：" + rows);
    }

    @Test
    public void deleteByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","刘明强");
        map.put("age",31);

        int rows = userMapper.deleteByMap(map);

        System.out.println("影响记录数：" + rows);
    }

    @Test
    public void deleteBatchIds(){
        List<Long> ids = Arrays.asList(
            1170735636174278657L,
            1170735727241048065L
        );

        int rows = userMapper.deleteBatchIds(ids);

        System.out.println("影响记录数：" + rows);
    }

    @Test
    public void delete(){
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getName,"刘明强").or().gt(User::getAge,50);

        int rows = userMapper.delete(query);

        System.out.println("影响记录数：" + rows);
    }

    @Test
    public void delete_entity(){
        User whereUser = new User();
        whereUser.setName("明强");//实体类中 @TableField(condition= SqlCondition.LIKE)仍然起作用

        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>(whereUser);
        query.lt(User::getAge,50);

        int rows = userMapper.delete(query);

        System.out.println("影响记录数：" + rows);
    }

}