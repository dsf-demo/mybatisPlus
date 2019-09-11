package com.dsf.mp.ar.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Model<User> {
    private static final long serialVersionUID = -3420609408231585157L;

    /**
     * 局部主键策略的设置：
     * AUTO(0)：数据库ID自增，跟随数据库，不适合于分布式场景
     * NONE(1)：默认，局部不设置ID时跟随全局策略
     * INPUT(2)：用户输入ID，在数据量很少时可以当测试使用
     *
     --以下三种类型仅在插入对象ID为空时，才自动填充
     * ID_WORKER(3)：数值类型的雪花算法主键，这也是全局默认的策略
     * UUID(4)：UUID是128位，它标识性强，但字符串过长会影响查询时间，对查询时间不敏感时使用
     * ID_WORKER_STR(5)：字符串类型的雪花算法主键
     */
    @TableId(type = IdType.NONE)
    private Long id;

    @TableField(condition= SqlCondition.LIKE)
    private String name;

    @TableField(condition= "%s&lt;#{%s}")
    private Integer age;

    @TableField(whereStrategy= FieldStrategy.NOT_EMPTY)
    private String email;

    private Long managerId;

    private LocalDateTime createTime;


}
