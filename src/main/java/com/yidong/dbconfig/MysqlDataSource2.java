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
@MapperScan(basePackages = "com.yidong.mysqlMapper2", sqlSessionTemplateRef = "mysqlSqlSessionTemplate2")
public class MysqlDataSource2 {

	@Bean(name = "mysqlDataSources2")
    @Qualifier("mysqlDataSources2")
    //这是主数据库链接对象 一定要添加的
    
    //读取数据库中的配置文件属性
    @ConfigurationProperties(prefix = "customss.datasource.mysql")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mysqlSqlSessionFactory2")
    public SqlSessionFactory secondarySqlSessionFactory(@Qualifier("mysqlDataSources2") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
        new PathMatchingResourcePatternResolver()
        //指定自己的数据库sql文件的地址  
        .getResources("classpath:static/mysqlMapper2/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "mysqlTransactionManager2")
    public PlatformTransactionManager secondaryTransactionManager(
            @Qualifier("mysqlDataSources2") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mysqlSqlSessionTemplate2")
    public SqlSessionTemplate secondarySqlSessionTemplate(
            @Qualifier("mysqlSqlSessionFactory2") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
