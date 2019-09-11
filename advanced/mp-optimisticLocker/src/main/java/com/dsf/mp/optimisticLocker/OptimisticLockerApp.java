package com.dsf.mp.optimisticLocker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dsf.mp.optimisticLocker.dao")
public class OptimisticLockerApp {
    public static void main(String[] args) {
        SpringApplication.run(OptimisticLockerApp.class, args);
    }
}
