package com.dsf.mp.fakeDelete.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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

    private  Integer version = 1;


    @TableLogic //逻辑删除标识(0.未删除,1.已删除)
    @TableField(select = false) //select中不包含本字段
    private  Integer deleted = 0;

}
