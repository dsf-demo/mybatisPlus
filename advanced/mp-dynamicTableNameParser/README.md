
###一、什么是动态表名
有点类似动态数据源；

###二、配置
与多租户配置相似
com.dsf.mp.dynamicTableNameParser.configuration.MybatisPlusConf
``` 
需要配合分页插件一起使用；
太长，不写了，直接参见文件吧
```

###三、方法级的动态表名
即，哪些方法让它有动态表名功能，哪些方法让它没有动态表名功能
####1.方式一：为分页拦截器添加过滤器
这种方式主要用于mp内置的方法  
具体实现仍然参见：com.dsf.mp.dynamicTableNameParser.configuration.MybatisPlusConf

####2.方式二：注解@SqlParser(filter = false)
这种方式主要用户自定义的方法  
具体实现参见：com.dsf.mp.dynamicTableNameParser.dao.UserMapper

 
###四、总结：
同多租户一样，动态表名似乎也有类似的一个bug,即如果自定义的sql表名用了上点符号（形如`user`），就不能识别出来
 
 
 
 
 
 
 