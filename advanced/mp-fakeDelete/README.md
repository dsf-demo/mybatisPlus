
实体类字段（deleted）添加注解：@TableLogic

配置（默认配置，也可以不配）：
resources/application.yml  
``` 
mybatis-plus:
  global-config:
    db-config:
      logic-delete-value: 1
      logic-not-delete-value: 0  
```
  
    