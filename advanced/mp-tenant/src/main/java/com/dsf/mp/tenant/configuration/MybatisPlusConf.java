package com.dsf.mp.tenant.configuration;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class MybatisPlusConf {

    @Bean
    PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        ArrayList<ISqlParser> sqlParsersList = new ArrayList<>();
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {

            /**
             * 租户ID，通常指公司代码或ID，获取途径包括：session、配置文件、枚举类、cookie、redis
             * 这里的ID是写死的，实际项目中要想办法获取
             */
            @Override
            public Expression getTenantId() {
                return new LongValue(1088248166370832385L);
            }

            /**
             * 指定租户ID的表列名
             */
            @Override
            public String getTenantIdColumn() {
                return "manager_id";
            }

            /**
             * 过滤掉（即不引入）哪些表的多租户功能
             * false-过滤，默认； true-不过滤；
             */
            @Override
            public boolean doTableFilter(String tableName) {
                if("user".equals(tableName)){
                    return false;
                }
                return true;
            }
        });

        sqlParsersList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParsersList);

        /**
         * 过滤掉（即不引入）哪些方法的sql解析（即不进行多租户功能）
         */
        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
            @Override
            public boolean doFilter(MetaObject metaObject) {
                MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
                if("com.dsf.mp.tenant.dao.UserMapper.selectById".equals(ms.getId())){
                    return true;
                }
                return false;
            }
        });

        return paginationInterceptor;
    }
}
