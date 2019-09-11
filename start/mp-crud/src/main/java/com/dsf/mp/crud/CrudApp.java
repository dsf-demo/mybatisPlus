package com.dsf.mp.crud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dsf.mp.crud.dao")
public class CrudApp {
    public static void main(String[] args) {
        SpringApplication.run(CrudApp.class, args);
    }
}
