package com.dsf.mp.performanceAnalysis.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dsf.mp.performanceAnalysis.PerformanceAnalysisApp;
import com.dsf.mp.performanceAnalysis.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PerformanceAnalysisApp.class)
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void selectById() throws InterruptedException {
        User user = userMapper.selectById(1171357729945505793L);
        System.out.println(user);
    }



}