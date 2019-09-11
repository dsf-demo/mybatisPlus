package com.dsf.mp.annotation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("mp_user")
public class User {

    @TableId(value = "user_id")
    private Long id;

    @TableField("user_name")
    private String name;

    private Integer age;

    private String email;

    private Long managerId;

    private LocalDateTime createTime;

    //transient关键字阻止字段的序列化，从而排除非表字段
    private transient String remark1;

    //static关键字公共化字段，从而排除非表字段
    public static String remark2;

    //通过mp提供的注解排除非表字段
    @TableField(exist=false)
    private String remark;


}
