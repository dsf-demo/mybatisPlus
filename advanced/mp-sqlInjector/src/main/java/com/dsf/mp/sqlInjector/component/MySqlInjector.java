package com.dsf.mp.sqlInjector.component;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.additional.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.injector.methods.additional.InsertBatchSomeColumn;
import com.baomidou.mybatisplus.extension.injector.methods.additional.LogicDeleteByIdWithFill;
import com.dsf.mp.sqlInjector.component.method.DeleteAllMethod;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MySqlInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        //一定要调用super.getMethodList,否则内置的方法全都不能用了
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);

        //添加自定的方法组件：删除全部
        methodList.add(new DeleteAllMethod());

        //添加选装件：批量插入
//        methodList.add(new InsertBatchSomeColumn(x->!x.isLogicDelete()));
        methodList.add(new InsertBatchSomeColumn(x->!x.isLogicDelete()&&!x.getColumn().equals("age")));//构造器中指定哪些字段插入，哪些不插入

        //添加选装件：逻辑删除并填充
        methodList.add(new LogicDeleteByIdWithFill());

        //添加选装件：根据id更新固定的字段
        methodList.add(new AlwaysUpdateSomeColumnById(x->!x.getColumn().equals("name")));//构造器中指定哪些字段更新，哪些不更新

        return methodList;
    }
}
