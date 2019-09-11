package com.dsf.mp.sqlInjector.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long id;

    private String name;

    @TableField(fill= FieldFill.UPDATE)
    private Integer age;

    private String email;

    private Long managerId;


    private LocalDateTime createTime;

    private  LocalDateTime updateTime;

    private  Integer version;

    @TableLogic
    @TableField(select=false)
    private  Integer deleted;

}
