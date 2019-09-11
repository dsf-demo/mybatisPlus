package com.dsf.mp.performanceAnalysis.configuration;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class MybatisPlusConf {

    /**
     * 引入性能分析插件
     * 性能分析插件和执行Sql打印插件选择一个即可
     */
    @Bean
    @Profile({"dev","test"})    //性能分析不应该在生产环境进行，这会损耗部分性能
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setFormat(true); //格式化sql语句
        performanceInterceptor.setMaxTime(5000L);  //执行sql超过5秒就报异常，即设置超时时间
        return performanceInterceptor;
    }
}
