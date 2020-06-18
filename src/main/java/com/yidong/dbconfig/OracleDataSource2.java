package com.yidong.dbconfig;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@MapperScan(basePackages = "com.yidong.orcaleMapperTwo", sqlSessionTemplateRef  = "orcaleSqlSessionTemplate2")
public class OracleDataSource2 {
	
	@Bean(name = "orcaleDataSources2")
    @Qualifier("orcaleDataSources2")
    @ConfigurationProperties(prefix = "customs.datasource.oracle")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "orcaleSqlSessionFactory2")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("orcaleDataSources2") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:static/oracleMapper2/*.xml"));
        return bean.getObject();
    }

    //配置声明式事务管理器
    @Bean(name = "orcaleTransactionManager2")
    public PlatformTransactionManager primaryTransactionManager(@Qualifier("orcaleDataSources2") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "orcaleSqlSessionTemplate2")
    public SqlSessionTemplate primarySqlSessionTemplate(
            @Qualifier("orcaleSqlSessionFactory2") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
