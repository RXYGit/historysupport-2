package com.yidong.dbconfig;


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

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.yidong.orcaleMapperThree", sqlSessionTemplateRef  = "orcaleSqlSessionTemplate3")
public class OracleDataSource3 {

    @Bean(name = "orcaleDataSources3")
    @Qualifier("orcaleDataSources3")
    @ConfigurationProperties(prefix = "customsss.datasource.oracle")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "orcaleSqlSessionFactory3")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("orcaleDataSources3") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:static/oracleMapper3/*.xml"));
        return bean.getObject();
    }

    //配置声明式事务管理器
    @Bean(name = "orcaleTransactionManager3")
    public PlatformTransactionManager primaryTransactionManager(@Qualifier("orcaleDataSources3") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "orcaleSqlSessionTemplate3")
    public SqlSessionTemplate primarySqlSessionTemplate(
            @Qualifier("orcaleSqlSessionFactory3") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
