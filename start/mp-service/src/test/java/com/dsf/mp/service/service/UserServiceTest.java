package com.dsf.mp.service.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dsf.mp.service.ServiceApp;
import com.dsf.mp.service.dao.UserMapper;
import com.dsf.mp.service.entity.User;
import com.dsf.mp.service.vo.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApp.class)
public class UserServiceTest {
    @Autowired
    IUserService userService;

    @Test
    public void getOne() {
        User one = userService.getOne(Wrappers.<User>lambdaQuery().gt(User::getAge,25),false);//如果查出多条不抛异常
        System.out.println(one);
    }

    /**
     * 批量插入
     */
    @Test
    public void saveBatch(){
        User user1 = new User();
        user1.setName("徐丽1");
        user1.setAge(28);

        User user2 = new User();
        user2.setName("徐丽2");
        user2.setAge(29);

        List<User> list = Arrays.asList(user1,user2);

        boolean success = userService.saveBatch(list);

        System.out.println("是否批量插入成功："+success);
    }

    /**
     * 批量插入或更新
     */
    @Test
    public void saveOrUpdateBatch(){
        User user1 = new User();
        user1.setName("徐丽3");
        user1.setAge(28);

        User user2 = new User();
        user2.setName("徐力2");
        user2.setAge(30);
        user2.setId(1170823840919412738L);

        List<User> list = Arrays.asList(user1,user2);

        boolean success = userService.saveOrUpdateBatch(list,1000);

        System.out.println("是否批量插入或更新成功："+success);
    }

    /**
     * lambdaQuery
     */
    @Test
    public void lambdaQuery(){
        List<User> list = userService.lambdaQuery().gt(User::getAge, 25).like(User::getName, "雨").list();
        list.forEach(System.out::println);
    }

    /**
     * lambdaUpdate
     */
    @Test
    public void lambdaUpdate(){
        boolean success = userService.lambdaUpdate()
            .eq(User::getAge, 25)
            .set(User::getAge,26)
            .update();
        System.out.println("是否更新成功："+success);
    }

    /**
     * lambdaUpdate_remove
     */
    @Test
    public void lambdaUpdate_remove(){
        boolean success = userService.lambdaUpdate()
            .gt(User::getAge, 50)
            .remove();
        System.out.println("是否更新_删除成功："+success);
    }


}