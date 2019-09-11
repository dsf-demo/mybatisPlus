package com.dsf.mp.autoFill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dsf.mp.autoFill.dao")
public class AutoFillApp {
    public static void main(String[] args) {
        SpringApplication.run(AutoFillApp.class, args);
    }
}
