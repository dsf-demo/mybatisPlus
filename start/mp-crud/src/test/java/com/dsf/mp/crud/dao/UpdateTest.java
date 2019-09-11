package com.dsf.mp.crud.dao;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.dsf.mp.crud.CrudApp;
import com.dsf.mp.crud.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrudApp.class)
public class UpdateTest {
    @Autowired
    UserMapper userMapper;

    /**
     * 更新:updateById
     */
    @Test
    public void updateById() {
        User user = new User();
        user.setId(1088248166370832385L);
        user.setAge(26);
        user.setEmail("wtf2@baomidou.com");
        int rows = userMapper.updateById(user);
        System.out.println("影响记录数：" + rows);
    }

    /**
     * 更新: 通过wrapper
     */
    @Test
    public void update() {
        UpdateWrapper<User> update = new UpdateWrapper<>();
        update.eq("name", "李艺伟").eq("age", 28);

        User user = new User();
        user.setAge(29);
        user.setEmail("lyw2@baomidou.com");
        int rows = userMapper.update(user, update);
        System.out.println("影响记录数：" + rows);
    }

    /**
     * 更新: 通过entity(或和wrapper)
     */
    @Test
    public void update_entity() {
        User whereUser = new User();
        whereUser.setName("李艺伟");//实体类中 @TableField(condition= SqlCondition.LIKE)仍然起作用

        UpdateWrapper<User> update = new UpdateWrapper<>(whereUser);
        update.eq("age", 29);

        User user = new User();
        user.setAge(30);
        user.setEmail("lyw3@baomidou.com");

        int rows = userMapper.update(user, update);
        System.out.println("影响记录数：" + rows);
    }

    /**
     * 更新:
     * 如果只更新1个或少量的几列，用set更简洁
     */
    @Test
    public void update_set() {
        UpdateWrapper<User> update = new UpdateWrapper<>();
        update.eq("name", "李艺伟")
            .eq("age", 30)
            .set("age", 31)
            .set("email", "lyw4@baomidou.com");

        int rows = userMapper.update(null, update);
        System.out.println("影响记录数：" + rows);
    }

    /**
     * 更新:
     * 使用LambdaUpdateWrapper可以防止列名被误写
     */
    @Test
    public void update_lambda() {
        LambdaUpdateWrapper<User> update = new LambdaUpdateWrapper<>();

        update.eq(User::getName, "李艺伟")
            .eq(User::getAge, 31)
            .set(User::getAge, 32)
            .set(User::getEmail, "lyw5@baomidou.com");

        int rows = userMapper.update(null, update);
        System.out.println("影响记录数：" + rows);
    }

    /**
     * 更新:
     * 使用LambdaUpdateChainWrapper可以使代码更简洁
     */
    @Test
    public void update_lambdaChain() {
        User user = new User();
        user.setAge(33);
        user.setEmail("lyw6@baomidou.com");

        boolean success = new LambdaUpdateChainWrapper<User>(userMapper)
            .eq(User::getName, "李艺伟")
            .eq(User::getAge, 32)
            .update(user);

        System.out.println("更新是否成功：" + success);
    }

    /**
     * 删除
     */
    @Test
    public void delete() {
        int rows = userMapper.deleteById(1170243901535006722L);

        System.out.println("影响记录数：" + rows);
    }

}