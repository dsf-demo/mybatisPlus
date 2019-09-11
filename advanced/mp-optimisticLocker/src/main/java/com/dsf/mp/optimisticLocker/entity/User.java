package com.dsf.mp.optimisticLocker.entity;

import com.baomidou.mybatisplus.annotation.*;
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

    /**
     * 乐观锁注解
     */
    @Version
    private  Integer version;

    private  Integer deleted;

}
