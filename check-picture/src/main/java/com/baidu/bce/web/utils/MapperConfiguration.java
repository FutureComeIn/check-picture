package com.baidu.bce.web.utils;

import com.baidu.bce.extension.core.database.MybatisBeanGenerator;
import com.baidu.bce.web.mapper.TelemetryMapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
public class MapperConfiguration {
    private MybatisBeanGenerator mybatisBeanGenerator;

    public MapperConfiguration(ConfigurableApplicationContext configurableApplicationContext) {
        MybatisBeanGenerator mybatisBeanGenerator = new MybatisBeanGenerator(configurableApplicationContext);
        this.mybatisBeanGenerator = mybatisBeanGenerator;
    }

    @Configuration
    @MapperScan(basePackageClasses = TelemetryMapper.class, sqlSessionFactoryRef = "sqlSessionFactory")
    public static class PlatformMapperScanConfiguration {

    }

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(@Autowired @Qualifier("dataSource") DataSource dataSource) {
        return mybatisBeanGenerator.createSqlSessionFactoryBean(dataSource);
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager) {
        return new TransactionTemplate(transactionManager);
    }
}