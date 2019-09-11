package com.dsf.mp.pagination.configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class MybatisPlusConf {
    @Bean
    PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
