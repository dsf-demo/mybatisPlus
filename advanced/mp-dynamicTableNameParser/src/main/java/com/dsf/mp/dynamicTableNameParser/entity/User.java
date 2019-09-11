package com.dsf.mp.dynamicTableNameParser.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long id;

    private String name;

    private Integer age;

    private String email;

    private Long managerId;

    private LocalDateTime createTime;

    private  LocalDateTime updateTime;

    private  Integer version;

    private  Integer deleted;

}
