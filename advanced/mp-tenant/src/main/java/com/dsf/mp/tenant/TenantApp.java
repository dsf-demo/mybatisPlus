package com.dsf.mp.tenant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dsf.mp.tenant.dao")
public class TenantApp {
    public static void main(String[] args) {
        SpringApplication.run(TenantApp.class, args);
    }
}
