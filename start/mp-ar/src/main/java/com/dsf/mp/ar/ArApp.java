package com.dsf.mp.ar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dsf.mp.ar.dao")
public class ArApp {
    public static void main(String[] args) {
        SpringApplication.run(ArApp.class, args);
    }
}
