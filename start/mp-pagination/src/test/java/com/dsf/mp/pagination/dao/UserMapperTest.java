package com.dsf.mp.pagination.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dsf.mp.pagination.PaginationApp;
import com.dsf.mp.pagination.entity.User;
import com.dsf.mp.pagination.vo.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaginationApp.class)
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    /**
     * 自定义Sql:
     * 1.注解形式：com.dsf.mp.demo4.dao.UserMapper
     * 2.xml文件形式：/resources/mapper/UserMapper.xml
     */
    @Test
    public void mySelect() {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.likeRight(User::getName, "王")
            .and(q -> q.lt(User::getAge, 40).or().isNotNull(User::getEmail));
        List<User> list = userMapper.selectAll(query);
        list.forEach(System.out::println);
    }

    /**
     * 单表分页:selectPage
     * 返回实体类列表
     */
    @Test
    public void selectPage() {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.ge(User::getAge,26).orderByDesc(User::getCreateTime);

        Page<User> page = new Page<> (1,2);
        userMapper.selectPage(page,query);

        System.out.println("总页数："+page.getPages());
        System.out.println("总记录数："+page.getTotal());
        List<User> list = page.getRecords();//分页返回的对象与传入的对象是同一个
        list.forEach(System.out::println);
    }

    /**
     * 单表分页:selectPage
     * 不查记录数
     */
    @Test
    public void selectPage_notSearchCount() {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.ge(User::getAge,26).orderByDesc(User::getCreateTime);

        Page<User> page = new Page<> (1,2,false);//不查记录数
        IPage<User>  iPage = userMapper.selectPage(page,query);//也可以用新Page对象接收返回的数据

        System.out.println("总页数："+iPage.getPages());
        System.out.println("总记录数："+iPage.getTotal());
        List<User> list = iPage.getRecords();
        list.forEach(System.out::println);
    }

    /**
     * 单表分页:selectMapsPage
     * 返回Map列表
     */
    @Test
    public void selectMapsPage() {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.ge(User::getAge,26).orderByDesc(User::getCreateTime);

        Page<User> page = new Page<> (1,2);
        IPage<Map<String,Object>>  iPage = userMapper.selectMapsPage(page,query);

        System.out.println("总页数："+page.getPages());
        System.out.println("总记录数："+iPage.getTotal());
        List<Map<String,Object>> list = iPage.getRecords();
        list.forEach(System.out::println);
    }

    /**
     * 自定义多表联查并分页
     *
     * ps: 本人认为，多表联查使用exists比使用join效率较高、通用性更强、sql语句更简单，
     * 虽然只查出单个主表的数据，但是业务层中可以根据需要，通过主表id再次查询出关联子表的对应页数据
     * 当然也可以在web端做异步访问查询，以防止一次查询出数据太多、耗时过长降低用户体验
     */
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