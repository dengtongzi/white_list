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
package cn.locker.config.datasource.multi;

import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

import cn.locker.core.config.properties.DruidProperties;
import cn.locker.core.datascope.DataScopeInterceptor;
import cn.locker.core.mutidatasource.mybatis.OptionalSqlSessionTemplate;
import cn.locker.core.util.ToolUtil;
import cn.locker.kernel.model.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数据源配置<br/>
 * <p>
 * 注：由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面
 *
 * @author dengtongzi
 * 
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "locker.muti-datasource", name = "open", havingValue = "true")
public class MultiSqlSessionFactoryConfig {

    /**
     * xml文件的位置，改包了注意修改哦
     */
    public static final String MAPPING_XML_CLASSPATH = "classpath:cn/locker/modular/**/mapping/*.xml";

    /**
     * 主sqlSessionFactory
     */
    @Primary
    @Bean
    public SqlSessionFactory sqlSessionFactoryPrimary(@Qualifier("dataSourcePrimary") DataSource dataSource) {
        return createSqlSessionFactory(dataSource);
    }

    /**
     * 第二个sqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactoryBusiness(@Qualifier("dataSourceBusiness") DataSource dataSource) {
        return createSqlSessionFactory(dataSource);
    }

    /**
     * 多数据源sqlSessionTemplate切换模板
     */
    @Bean(name = "lockerSqlSessionTemplate")
    public OptionalSqlSessionTemplate lockerSqlSessionTemplate(@Qualifier("sqlSessionFactoryPrimary") SqlSessionFactory sqlSessionFactoryPrimary,
                                                             @Qualifier("sqlSessionFactoryBusiness") SqlSessionFactory sqlSessionFactoryBusiness,
                                                             @Qualifier("druidProperties") DruidProperties druidProperties,
                                                             @Qualifier("mutiDataSourceProperties") DruidProperties mutiDataSourceProperties) {
        if (ToolUtil.isOneEmpty(druidProperties, druidProperties.getDataSourceName())) {
            throw new IllegalArgumentException("初始化OptionalSqlSessionTemplate错误！请设置spring.datasource.data-source-name属性的值！");
        }

        if (ToolUtil.isOneEmpty(mutiDataSourceProperties, mutiDataSourceProperties.getDataSourceName())) {
            throw new IllegalArgumentException("初始化OptionalSqlSessionTemplate错误！请设置spring.muti-datasource.data-source-name属性的值！");
        }

        Map<Object, SqlSessionFactory> sqlSessionFactoryMap = new HashMap<>();
        sqlSessionFactoryMap.put(druidProperties.getDataSourceName(), sqlSessionFactoryPrimary);
        sqlSessionFactoryMap.put(mutiDataSourceProperties.getDataSourceName(), sqlSessionFactoryBusiness);
        return new OptionalSqlSessionTemplate(sqlSessionFactoryPrimary, sqlSessionFactoryMap);
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 数据范围mybatis插件
     */
    @Bean
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor();
    }

    /**
     * 乐观锁mybatis插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 创建数据源
     */
    private SqlSessionFactory createSqlSessionFactory(DataSource dataSource) {
        try {
            MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
            bean.setDataSource(dataSource);
            bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPING_XML_CLASSPATH));
            bean.setVfs(SpringBootVFS.class);
            bean.setPlugins(new Interceptor[]{
                    paginationInterceptor(),
                    dataScopeInterceptor(),
                    optimisticLockerInterceptor()
            });
            return bean.getObject();
        } catch (Exception e) {
            log.error("初始化SqlSessionFactory错误！", e);
            throw new ServiceException(500, "初始化SqlSessionFactory错误！");
        }
    }

}
