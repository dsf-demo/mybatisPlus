package com.dsf.mp.autoFill.entity;

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

    /**
     * 当有insert语句时，自动填充
     * {@link com.dsf.mp.autoFill.component.MyMetaObjectHandler}
     */
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 当有insert或update语句时，自动填充
     * {@link com.dsf.mp.autoFill.component.MyMetaObjectHandler}
     */
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private  LocalDateTime updateTime;

    private  Integer version = 1;

    private  Integer deleted = 0;

}
