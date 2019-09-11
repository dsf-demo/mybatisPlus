package com.dsf.mp.service.dao;

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

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApp.class)
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void selectUserPage() {
        UserVo userVo = new UserVo();
        userVo.setAgeStart(25);
        userVo.setHobby("学");

        Page<User> page = new Page<> (1,2);
        userMapper.selectUserPage(page,userVo);

        System.out.println("总页数："+page.getPages());
        System.out.println("总记录数："+page.getTotal());
        List<User> list = page.getRecords();
        list.forEach(System.out::println);
    }


}