package com.dsf.mp.autoFill.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * mp提供的对公共字段的处理器
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //setInsertFieldValByName会消耗部分性能，所以可以先判断实体类中是否有"createTime"字段
        boolean hasCreateTimeSetter = metaObject.hasSetter("createTime");
        if(hasCreateTimeSetter){
            //注意这里拦截的是java字段createTime，不是mysql表列名create_time
            setInsertFieldValByName("createTime", LocalDateTime.now(),metaObject);
            //也可以采用下面这种写法
//        setFieldValByName("createTime", LocalDateTime.now(),metaObject, FieldFill.INSERT);
        }

        boolean hasUpdateTimeSetter = metaObject.hasSetter("updateTime");
        if(hasUpdateTimeSetter){
            setInsertFieldValByName("updateTime",LocalDateTime.now(),metaObject);
        }


    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //如果上游没有设置"updateTime"字段，再进行填充，设置了就不填充
        Object val = getFieldValByName("updateTime",metaObject);
        if(null==val){
            setUpdateFieldValByName("updateTime", LocalDateTime.now(),metaObject);
        }

    }
}
