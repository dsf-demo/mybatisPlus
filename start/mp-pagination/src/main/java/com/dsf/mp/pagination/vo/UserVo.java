package com.dsf.mp.pagination.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVo {
    private Long id;

    private String name;

    //年龄大于等于ageStart的
    private Integer ageStart;

    //年龄小于等于ageEnd的
    private Integer ageEnd;

    private String email;

    private Long managerId;

    private LocalDateTime createTime;

    private  String hobby;

}
