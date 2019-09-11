package com.dsf.mp.fakeDelete.configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class MybatisPlusConf {
    /**
     * 在新版3.1.2中默认自动添加了ISqlInjector插件，
     * 所以LogicSqlInjector是过时的，这里可以不用注入此Bean
     */
//    @Bean
//    public ISqlInjector sqlInjector(){
//        return new LogicSqlInjector();
//    }


}
