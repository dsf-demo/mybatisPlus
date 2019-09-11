


实体类字段（version）添加注解：@Version  

配置插件：
com.dsf.mp.optimisticLocker.configuration.MybatisPlusConf 
```
@Configuration
public class MybatisPlusConf {
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }
}
```



总结：
依我之见，在某些应用场景下，乐观锁或是悲观锁，都是在客户操作数据之后通知客户操作失败的，这会降低用户体验。
我们应该在客户操作数据之前，就通知客户是否有其他用户正在操作同一数据，并将数据设置为只读状态禁止用户操作
    