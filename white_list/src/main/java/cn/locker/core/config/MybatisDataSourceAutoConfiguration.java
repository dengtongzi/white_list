/**
 * Copyright 2018-2020 dengtongzi  
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.locker.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

import cn.locker.core.config.properties.DruidProperties;
import cn.locker.core.metadata.CustomMetaObjectHandler;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置
 *
 * @author dengtongzi
 * 
 */
@Configuration
@MapperScan(basePackages = {"**.mapper"})
@ConditionalOnProperty(prefix = "spring.datasource", name = "url")
public class MybatisDataSourceAutoConfiguration {

    @Autowired
    private DruidProperties druidProperties;

    /**
     * druid配置
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidProperties druidProperties() {
        return new DruidProperties();
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        if (druidProperties.getUrl().contains("oracle")) {
            paginationInterceptor.setDialectType(DbType.ORACLE.getDb());
        } else if (druidProperties.getUrl().contains("postgresql")) {
            paginationInterceptor.setDialectType(DbType.POSTGRE_SQL.getDb());
        } else if (druidProperties.getUrl().contains("sqlserver")) {
            paginationInterceptor.setDialectType(DbType.SQL_SERVER2005.getDb());
        } else {
            paginationInterceptor.setDialectType(DbType.MYSQL.getDb());
        }
        return paginationInterceptor;
    }

    /**
     * druid数据库连接池
     */
    @Bean(initMethod = "init")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 自定义公共字段自动注入
     */
    @ConditionalOnMissingBean
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new CustomMetaObjectHandler();
    }

}
