package com.dsf.mp.optimisticLocker.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dsf.mp.optimisticLocker.OptimisticLockerApp;
import com.dsf.mp.optimisticLocker.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OptimisticLockerApp.class)
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void updateById() throws InterruptedException {
        User user = userMapper.selectById(1171357729945505793L);
        user.setAge(33);

        Thread.sleep(30000);
        System.out.println("在修改的过程中，有其他用户对数据进行了更新...");
        System.out.println("去数据库修改该条数据，并将version增加一个版本");

        int rows = userMapper.updateById(user);
        System.out.println("影响行数："+rows);
    }


    /**
     * 在 update(entity, wrapper) 方法下, wrapper 不能复用!!!
     * 原因：
     *   每应用一次QueryWrapper,都会自动为where子句添加一次version=?，两次应用就会添加两次，
     *   出现形如“where ... and version=2 and version=3”的情况
     * 解决：
     *   重新创建一个QueryWrapper对象
     */
    @Test
    public void update() throws InterruptedException {
        User user = userMapper.selectById(1171357729945505793L);
        user.setAge(34);

        LambdaQueryWrapper<User> query = Wrappers.<User>lambdaQuery().eq(User::getId, user.getId());

        int rows1 = userMapper.update(user,query);//第一次用query
        System.out.println("影响行数："+rows1);


        user = userMapper.selectById(1171357729945505793L);
        user.setAge(35);
        int rows2 = userMapper.update(user,query);//复用query
        System.out.println("影响行数："+rows2);
    }



}