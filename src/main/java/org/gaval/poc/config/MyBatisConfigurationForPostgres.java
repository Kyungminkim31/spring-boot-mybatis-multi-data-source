package org.gaval.poc.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@MapperScan(
		basePackages = "org.gaval.poc.user"
		, sqlSessionTemplateRef = MyBatisConfigurationForPostgres.POSTGRES_SESSION_TEMPLATE)
public class MyBatisConfigurationForPostgres {
	public static final String POSTGRES_SESSION_FACTORY = "postgresSessionFactory";
	public static final String POSTGRES_SESSION_TEMPLATE = "postgresSessionTemplate";
	
	
	@Bean(name = POSTGRES_SESSION_FACTORY)
	@Primary
	public SqlSessionFactory sqlSessionFactoryForPostgres(
			@Qualifier(DataSourceConfigurations.POSTGRES_DS)
			final DataSource postgresDataSource,
			ApplicationContext applicationContext
			) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(postgresDataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext
				.getResources("classpath*:mybatis/postgresql/**/*.xml"));
		sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
		
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean(name = POSTGRES_SESSION_TEMPLATE)
	@Primary
	public SqlSessionTemplate sqlSessionTemplateForPostgres(
			@Qualifier(POSTGRES_SESSION_FACTORY)
			final SqlSessionFactory postgreSqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(postgreSqlSessionFactory);
	}

}
