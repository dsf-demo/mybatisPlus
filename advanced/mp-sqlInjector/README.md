
###一、为什么要有sql注入
sql注入使我们能够使用自定义的mapper接口方法，就像使用selectById、delete等内置crud方法一样

###二、配置
####1.自定义方法类
com.dsf.mp.sqlInjector.component.method.DeleteAllMethod  

####2.继承DefaultSqlInjector重写getMethodList方法，添加自定义方法组件
com.dsf.mp.sqlInjector.component.MySqlInjector  

####3.继承BaseMapper接口，按照自定义方法组件所指定的方法名称添加方法
com.dsf.mp.sqlInjector.dao.MyBaseMapper  

###三、总结
1. mp内置了一些可选组件：  
    InsertBatchSomeColumn、  
    LogicDeleteByIdWithFill、  
    AlwaysUpdateSomeColumnById  
    
2. 一些内置组件，可能测试并不全面，使用时需谨慎  


3. 如何设置自定义组件方法的参数呢？比如设置deleteAll方法的参数?

 
 
 
 
 
 
 
 