package com.dsf.mp.dynamicTableNameParser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dsf.mp.dynamicTableNameParser")
public class DynamicTableNameParserApp {
    public static void main(String[] args) {
        SpringApplication.run(DynamicTableNameParserApp.class, args);
    }
}
