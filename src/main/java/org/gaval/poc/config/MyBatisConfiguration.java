package org.gaval.poc.config;

import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@MapperScan(basePackages = "org.gaval.poc")
public class MyBatisConfiguration {
	private static final String MYSQL_SESSION_FACTORY = "mysqlSessionFactory";
	private static final String POSTGRES_SESSION_FACTORY = "postgresSessionFactory";
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Bean(name = POSTGRES_SESSION_FACTORY)
	@Primary
	public SqlSessionFactory sqlSessionFactoryForPostgres(
			@Named(DataSourceConfigurations.POSTGRES_DS)
			final DataSource postgresDataSource
			) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(postgresDataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:mybatis/postgresql/**/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	@Primary
	public SqlSessionTemplate sqlSessionTemplateForPostgres(
			@Named(POSTGRES_SESSION_FACTORY)
			final SqlSessionFactory postgreSqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(postgreSqlSessionFactory);
	}
	
	@Bean(name = MYSQL_SESSION_FACTORY)
	public SqlSessionFactory sqlSessionFactoryForMySQL(
			@Named(DataSourceConfigurations.MYSQL_DS)
			final DataSource mySqlDataSource
			) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(mySqlDataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:mybatis/mysql/**/*.xml"));	
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplateForMySQL(
			@Named(MYSQL_SESSION_FACTORY)
			final SqlSessionFactory postgreSqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(postgreSqlSessionFactory);
	}

}
