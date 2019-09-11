package com.dsf.mp.performanceAnalysis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dsf.mp.performanceAnalysis.dao")
public class PerformanceAnalysisApp {
    public static void main(String[] args) {
        SpringApplication.run(PerformanceAnalysisApp.class, args);
    }
}
