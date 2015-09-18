package org.yood.springboot.mybatis;

import org.yood.springboot.mybatis.config.DataSourceProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PreDestroy;


@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(DataSourceProperties.class)
@MapperScan(DataSourceConfig.MAPPER_PACKAGE)
public class DataSourceConfig {

    public static final String MAPPER_PACKAGE = "org.yood.springboot.mybatis.mapper";
    private static final String TYPE_ALIASES_PACKAGE = "org.yood.springboot.mybatis.entity";

    @Autowired
    private DataSourceProperties dataSourceProperties;

    private DataSource dataSource;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        dataSource = new DataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUserName());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setInitialSize(dataSourceProperties.getInitialSize());
        dataSource.setMaxActive(dataSourceProperties.getMaxActive());
        dataSource.setMaxIdle(dataSourceProperties.getMaxIdle());
        dataSource.setMinIdle(dataSourceProperties.getMinIdle());
        dataSource.setValidationQuery(dataSourceProperties.getValidationQuery());
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @PreDestroy
    public void close() {
        if (dataSource != null) {
            dataSource.close();
            dataSource = null;
        }
    }
}
