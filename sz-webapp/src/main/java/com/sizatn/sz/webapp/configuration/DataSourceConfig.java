package com.sizatn.sz.webapp.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * @desc 数据源配置
 * @author sizatn
 * @date Aug 9, 2017
 */
@Configuration
public class DataSourceConfig {

	/** 精确到 primary 目录，以便跟其他数据源隔离 */ 
	private static final String PACKAGE = "com.sizatn.sz.webapp.dao.primary";
	private static final String MAPPER_LOCATION = "classpath:mapper/*.xml";
	private static final String SESSION_FACTORY_BEAN_NAME = "primarySqlSessionFactory";
	private static final String TYPE_ALIASES_PACKAGE = "com.sizatn.sz.webapp.entity";
	
	@Primary
	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public DataSource dataSource() {
		return DataSourceBuilder.create().type(DruidDataSource.class).build();
//		return primaryDataSourceProperties().initializeDataSourceBuilder().type(DruidDataSource.class).build();
	}

	@Primary
	@Bean(name = "transactionManager")
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Primary
	@Bean(name = "primarySqlSessionFactory")
	public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource primaryDataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(primaryDataSource);
		sessionFactory.setTypeAliasesPackage(DataSourceConfig.TYPE_ALIASES_PACKAGE);
		
//		PageInterceptor pageInterceptor = new PageInterceptor();
//		Properties properties = new Properties();
//		properties.setProperty("helperDialect", "mysql");
//		properties.setProperty("reasonable", "true");
//		properties.setProperty("supportMethodsArguments", "true");
//		properties.setProperty("params", "count=countSql");
//		pageInterceptor.setProperties(properties);
//		sessionFactory.setPlugins(new Interceptor[] { pageInterceptor });
		
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DataSourceConfig.MAPPER_LOCATION));
		return sessionFactory.getObject();
	}
	
	@Primary
	@Bean(name = "mapperScannerConfigurer")
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setSqlSessionFactoryBeanName(DataSourceConfig.SESSION_FACTORY_BEAN_NAME);
        // 与接口上的@Mapper注解作用相同，选一种配置方式即可
        msc.setBasePackage(DataSourceConfig.PACKAGE);
        return msc;
    }
	
	@Primary
	@Bean(name = "jdbcTemplate")
	public JdbcTemplate primaryJdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
