
###一、什么是多租户
多租户大体有三个级别：库级、schema级、表级
库级：即不同的租户使用不同的数据库，这需要很大的采购成本及维护成本
schema级：不同的租户，采用同一个数据库，但采用不同的schema。对于mysql，库和schema是等同的。
表级：不同的租户，采用同一个数据库、同一个schema，同一个表中用某一列名(比如`tenant_id`)区分不同的租户
mybatis-plus的多租户功能即采用的表级别的。

###二、配置
com.dsf.mp.tenant.configuration.MybatisPlusConf
``` 
多租户需要配合分页插件一起使用；
太长，不写了，直接参见文件吧
```

###三、方法级的多租户功能
####1.方式一：为分页拦截器添加过滤器
这种方式主要用于mp内置的方法  
具体实现仍然参见：com.dsf.mp.tenant.configuration.MybatisPlusConf  

####2.方式二：注解@SqlParser(filter = false)
这种方式主要用户自定义的方法  
具体实现参见：com.dsf.mp.tenant.dao.UserMapper


 
###四、总结：
mp3.1.2多租户似乎有个bug，参见：
[自定义sql表名使用上点符号时，TenantHandler#doTableFilter的参数tableName不识别表名](https://github.com/baomidou/mybatis-plus/issues/1611)
 
在issue未关闭之前，在自定义sql时，应该尽量避免使用包含上点符号的表名，如`user`  
 
 
 
 
 
 
 
 
 