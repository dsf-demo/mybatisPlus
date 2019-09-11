package com.dsf.mp.performanceAnalysis.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class User {
    private Long id;

    @TableField(condition= SqlCondition.LIKE)
    private String name;

    @TableField(condition= "%s&lt;#{%s}")
    private Integer age;

    private String email;

    private Long managerId;

    private LocalDateTime createTime;

    private  LocalDateTime updateTime;

    /**
     * 乐观锁
     */
    @Version
    private  Integer version;

    private  Integer deleted;

}
