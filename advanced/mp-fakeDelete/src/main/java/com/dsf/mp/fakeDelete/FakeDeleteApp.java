package com.dsf.mp.fakeDelete;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dsf.mp.fakeDelete.dao")
public class FakeDeleteApp {
    public static void main(String[] args) {
        SpringApplication.run(FakeDeleteApp.class, args);
    }
}
