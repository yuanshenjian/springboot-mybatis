package org.yood.springboot.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
@EnableConfigurationProperties(DataSourceConfig.DataSourceProperties.class)
@MapperScan(DataSourceConfig.MAPPER_PACKAGE)
public class DataSourceConfig {

    public static final String MAPPER_PACKAGE = "org.yood.springboot.mybatis.mapper";
    private static final String TYPE_ALIASES_PACKAGE = "org.yood.springboot.mybatis.entity";

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
    private DataSourceProperties dataSourceProperties;

    private DataSource dataSource;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        LOGGER.info("Data source properties = {}", dataSourceProperties);
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

    @ConfigurationProperties(prefix = "datasource",
                             ignoreUnknownFields = false)
    public static class DataSourceProperties {

        private String driverClassName;
        private String url;
        private String userName;
        private String password;
        private String validationQuery;
        private int maxActive;
        private int maxIdle;
        private int minIdle;
        private int initialSize;

        public String getDriverClassName() {
            return driverClassName;
        }

        public String getUrl() {
            return url;
        }

        public String getUserName() {
            return userName;
        }

        public String getPassword() {
            return password;
        }

        public int getMaxActive() {
            return maxActive;
        }

        public int getMaxIdle() {
            return maxIdle;
        }

        public int getMinIdle() {
            return minIdle;
        }

        public int getInitialSize() {
            return initialSize;
        }

        public String getValidationQuery() {
            return validationQuery;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setValidationQuery(String validationQuery) {
            this.validationQuery = validationQuery;
        }

        public void setMaxActive(int maxActive) {
            this.maxActive = maxActive;
        }

        public void setMaxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
        }

        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

        public void setInitialSize(int initialSize) {
            this.initialSize = initialSize;
        }

        @Override
        public String toString() {
            return "DataSourceProperties{" +
                    "driverClassName='" + driverClassName + '\'' +
                    ", url='" + url + '\'' +
                    ", userName='" + userName + '\'' +
                    ", password='" + password + '\'' +
                    ", maxActive=" + maxActive +
                    ", maxIdle=" + maxIdle +
                    ", minIdle=" + minIdle +
                    ", initialSize=" + initialSize +
                    ", validationQuery='" + validationQuery + '\'' +
                    '}';
        }
    }
}
