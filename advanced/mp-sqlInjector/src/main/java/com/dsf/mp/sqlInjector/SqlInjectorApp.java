package com.dsf.mp.sqlInjector;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dsf.mp.sqlInjector.dao")
public class SqlInjectorApp {
    public static void main(String[] args) {
        SpringApplication.run(SqlInjectorApp.class, args);
    }
}
