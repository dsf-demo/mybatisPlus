package com.dsf.mp.crud.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
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
public class SelectTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void selectById() {
        User user = userMapper.selectById(1087982257332887553L);
        System.out.println(user);
    }

    @Test
    public void selectBatchIds() {
        List<Long> ids = Arrays.asList(
            1087982257332887553L,
            1094590409767661570L,
            1094592041087729666L
        );
        List<User> list = userMapper.selectBatchIds(ids);
        list.forEach(System.out::println);
    }

    @Test
    public void selectByMap() {
        Map<String, Object> map = new HashMap<>();
        //map的key指代的是mysql表中的列名，并非java实体的属性名
        map.put("name", "张雨琪");
        map.put("manager_id", 1088248166370832385L);
        List<User> list = userMapper.selectByMap(map);
        list.forEach(System.out::println);
    }

    @Test
    public void selectList_all() {
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    /**
     * 名字中包含雨，并且年龄小于40
     * SELECT * FROM `user`
     * WHERE `name` LIKE '%雨%' AND `age`< 40
     */
    @Test
    public void selectList_like_lt() {
//        QueryWrapper<User> query = Wrappers.<User>query();
        QueryWrapper<User> query = new QueryWrapper<>();
        query.like("name", "雨").lt("age", 40);
        List<User> list = userMapper.selectList(query);
        list.forEach(System.out::println);
    }

    /**
     * 名字中包含雨，并且年龄大于等于20且小于等于40，并且email不为空
     * SELECT * FROM `user`
     * WHERE `name` LIKE '%雨%' AND `age` <= 40 AND `age` >= 20 AND `email` IS NOT NULL
     */
    @Test
    public void selectList_between_isNotNull() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.like("name", "雨").between("age", 20, 40).isNotNull("email");
        List<User> list = userMapper.selectList(query);
        list.forEach(System.out::println);
    }

    /**
     * 姓赵或者年龄大于等于25，按照年龄降序排列，年龄相同则按照id升序排列
     * SELECT * FROM `user`
     * WHERE `name` LIKE '赵%' OR `age` >= 25 ORDER BY `age` DESC , `id` ASC;
     */
    @Test
    public void selectList_or_orderByDesc_orderByAsc() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.likeRight("name", "赵").or().ge("age", 20)
            .orderByDesc("age").orderByAsc("id");
        List<User> list = userMapper.selectList(query);
        list.forEach(System.out::println);
    }

    /**
     * 创建日期为2019年2月14日，且直属上级姓王
     * SELECT * FROM `user`
     * WHERE DATE_FORMAT(create_time,'%Y-%m-%d')='2019-02-14'
     * AND manager_id IN (SELECT id FROM `user` WHERE `name` LIKE '王%')
     */
    @Test
    public void selectList_apply_inSql() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.apply("DATE_FORMAT(create_time,'%Y-%m-%d')={0}", "2019-02-14")
            .inSql("manager_id", "SELECT id FROM `user` WHERE `name` LIKE '王%'");
        List<User> list = userMapper.selectList(query);
        list.forEach(System.out::println);
    }

    /**
     * 姓王且（年龄小于40或邮箱不为空）
     * SELECT * FROM `user`
     * WHERE `name` LIKE '王%' AND (`age`< 40 OR `email` IS NOT NULL)
     */
    @Test
    public void selectList_and_lambda() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.likeRight("name", "王")
            .and(q -> q.lt("age", 40).or().isNotNull("email"));
        List<User> list = userMapper.selectList(query);
        list.forEach(System.out::println);
    }

    /**
     * 姓王且或者（年龄小于40且年龄大于20且邮箱不为空）
     * SELECT * FROM `user`
     * WHERE `name` LIKE '王%' OR (`age`< 40 AND `age` > 20  AND `email` IS NOT NULL)
     */
    @Test
    public void selectList_or_lambda() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.likeRight("name", "王")
            .or(q -> q.lt("age", 40).gt("age", 20).isNotNull("email"));
        List<User> list = userMapper.selectList(query);
        list.forEach(System.out::println);
    }

    /**
     * （年龄小于40或邮箱不为空）且姓王
     * SELECT * FROM `user`
     * WHERE (`age`< 40 OR `email` IS NOT NULL) AND `name` LIKE '王%'
     */
    @Test
    public void selectList_nested() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.nested(q -> q.lt("age", 40).or().isNotNull("email"))
            .likeRight("name", "王");
        List<User> list = userMapper.selectList(query);
        list.forEach(System.out::println);
    }

    /**
     * 年龄为30,31,34,35
     * SELECT * FROM `user` WHERE `age` IN (30,31,34,35);
     */
    @Test
    public void selectList_in() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.in("age", Arrays.asList(30, 31, 34, 35));
        List<User> list = userMapper.selectList(query);
        list.forEach(System.out::println);
    }

    /**
     * 返回只满足条件的一条（只调用最后一次，有sql注入的风险）
     * SELECT * FROM `user` WHERE `age` IN (30,31,34,35) LIMIT 1;
     */
    @Test
    public void selectList_last() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.in("age", Arrays.asList(30, 31, 34, 35)).last("limit 1");
        List<User> list = userMapper.selectList(query);
        list.forEach(System.out::println);
    }

    /**
     * 只查询指定字段
     * SELECT `name`,`age` FROM `user` WHERE `age` IN (30,31,34,35) LIMIT 1;
     */
    @Test
    public void selectList_select_include() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.select("name", "age").in("age", Arrays.asList(30, 31, 34, 35)).last("limit 1");
        List<User> list = userMapper.selectList(query);
        list.forEach(System.out::println);
    }

    /**
     * 排除指定字段
     */
    @Test
    public void selectList_select_exclude() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.in("age", Arrays.asList(30, 31, 34, 35)).last("limit 1")
            .select(
                User.class,
                info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("manager_id")
            );
        List<User> list = userMapper.selectList(query);
        list.forEach(System.out::println);
    }

    /**
     * 条件判断
     */
    @Test
    public void selectList_condition() {
        String name = "刘";
        String email = "";
        QueryWrapper<User> query = new QueryWrapper<>();
        query.like(StringUtils.isNotEmpty(name), "name", name)
            .like(StringUtils.isNotEmpty(email), "email", email);
        List<User> list = userMapper.selectList(query);
        list.forEach(System.out::println);
    }

    /**
     * 实体类作为条件构造器
     * 默认是等值查询，可以在实体类属性中设置自定义条件
     */
    @Test
    public void selectList_entity() {
        User whereUser = new User();
        whereUser.setName("刘");//name like "刘"
        whereUser.setAge(32);//age<30
        QueryWrapper<User> query = new QueryWrapper<>(whereUser);
        query.eq("manager_id", "1088248166370832385");

        List<User> list = userMapper.selectList(query);
        list.forEach(System.out::println);
    }

    /**
     * allEq
     */
    @Test
    public void selectList_allEq() {
        QueryWrapper<User> query = new QueryWrapper<>();
        Map<String, Object> params = new HashMap<>();
        params.put("name", "刘明强");
        params.put("age", 31);
        params.put("email", null);
//        query.allEq(params,false);//第二个参数表示如果列值为null是否按IS NULL查询，false则忽略null列的查询
        query.allEq((k, v) -> !k.equals("name"), params, false);//第一个参数是过滤器
        List<User> list = userMapper.selectList(query);
        list.forEach(System.out::println);
    }

    /**
     * selectMaps的应用场景1：当表中的列特别多，但实际只需要几个列时，这时返回一个实体类有些不必要
     */
    @Test
    public void selectMaps() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.like("name", "雨").lt("age", 40).select("name", "age");
        List<Map<String, Object>> maps = userMapper.selectMaps(query);
        maps.forEach(System.out::println);
    }

    /**
     * selectMaps的应用场景2：查询统计结果
     * 按照直属上级分组，查询每组的平均年龄、最大年龄、最小年龄，并且只取年龄总和小于100的组
     * SELECT AVG(age) avg_age,MIN(age) min_age,MAX(age) max_age
     * FROM `user`
     * GROUP BY `manager_id`
     * HAVING SUM(age)<100;
     */
    @Test
    public void selectMaps2() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.select("AVG(age) avg_age", "MIN(age) min_age", "MAX(age) max_age")
            .groupBy("manager_id")
            .having("SUM(age)<{0}", 100);
        List<Map<String, Object>> maps = userMapper.selectMaps(query);
        maps.forEach(System.out::println);
    }


    /**
     * selectObjs只返回第一列，其它列被遗弃
     * 应用场景：只需返回一列的时候
     */
    @Test
    public void selectObjs() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.like("name", "雨").lt("age", 40).select("name", "age");
        List<Object> list = userMapper.selectObjs(query);
        list.forEach(System.out::println);
    }

    /**
     * 返回总记录数
     */
    @Test
    public void selectCount() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.like("name", "雨").lt("age", 40);
        Integer count = userMapper.selectCount(query);
        System.out.println("总记录数：" + count);
    }

    /**
     * selectOne：只能查询一条记录，查询到多条会报错
     */
    @Test
    public void selectOne() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.like("name", "刘红雨").lt("age", 40);
        User user = userMapper.selectOne(query);
        System.out.println(user);
    }

    /**
     * lambda条件构造器
     */
    @Test
    public void lambdaQueryWrapper1() {
//        LambdaQueryWrapper<User> lambdaQ = new QueryWrapper<User>().lambda();
//        LambdaQueryWrapper<User> lambdaQ = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> lambdaQ = Wrappers.<User>lambdaQuery();

        lambdaQ.like(User::getName, "雨").lt(User::getAge, 40);
        List<User> list = userMapper.selectList(lambdaQ);
        list.forEach(System.out::println);
    }

    /**
     * lambda条件构造器:防误写（例如列名"name"可能被误写）
     */
    @Test
    public void lambdaQueryWrapper2() {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.likeRight(User::getName, "王")
            .and(q -> q.lt(User::getAge, 40).or().isNotNull(User::getEmail));
        List<User> list = userMapper.selectList(query);
        list.forEach(System.out::println);
    }

    /**
     * 链式lambda条件构造器：更优雅的书写方式
     */
    @Test
    public void lambdaQueryChainWrapper() {
        List<User> list = new LambdaQueryChainWrapper<User>(userMapper)
            .likeRight(User::getName, "王")
            .and(
                q -> q
                    .lt(User::getAge, 40)
                    .or()
                    .isNotNull(User::getEmail)
            )
            .list();
        list.forEach(System.out::println);
    }

}