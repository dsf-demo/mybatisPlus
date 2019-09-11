

###一、mp性能分析插件（3.2.0以上版本移除）
配置： 
com.dsf.mp.performanceAnalysis.configuration.MybatisPlusConf
``` 
@Bean
@Profile({"dev","test"})    //性能分析不应该在生产环境进行，这会损耗部分性能
public PerformanceInterceptor performanceInterceptor(){
    PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
    performanceInterceptor.setFormat(true); //格式化sql语句
    performanceInterceptor.setMaxTime(5000L);  //执行sql超过5秒就报异常，即设置超时时间
    return performanceInterceptor;
}
```

###二、第三方执行SQL分析打印插件p6spy（这是官方推荐使用的）
####1. pom.xml  
``` 
<dependency>
    <groupId>p6spy</groupId>
    <artifactId>p6spy</artifactId>
    <version>3.8.5</version>
</dependency>
```

####2. resources/spy.properties
``` 
太长，不写了，直接参见文件吧
```
 
####3.resources/application-dev.yml
``` 
spring:
  datasource:
    driver-class-name: com.p6spy.engine.spy.P6SpyDriver
    url: jdbc:p6spy:mysql://testhost:3306/mp?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
    #other
```
 
 ###三、总结：
 注意该项目的pom.xml文件，与其它项目是有差别的，所有的改变都是为了区分开：开发环境和生产环境
 
 两个插件的共同点：  
 功能相同，但第三方插件p6spy更强大一些；  
 都会损耗机器性能，应避免在生产环境使用； 
 
 
 
 
 
 
 
 
 
 