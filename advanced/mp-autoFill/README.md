
实体类字段（version）添加注解：@TableField(fill= FieldFill.xxx)

配置插件：
com.dsf.mp.autoFill.component.MyMetaObjectHandler 
```
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //do something...
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //do something...
    }
}
```
    