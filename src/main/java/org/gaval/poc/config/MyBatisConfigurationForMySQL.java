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

@Configuration
@MapperScan(
		basePackages = "org.gaval.poc.app"
		, sqlSessionTemplateRef = MyBatisConfigurationForMySQL.MYSQL_SESSION_TEMPLATE
)
public class MyBatisConfigurationForMySQL {
	public static final String MYSQL_SESSION_FACTORY = "mysqlSessionFactory";
	public static final String MYSQL_SESSION_TEMPLATE = "mysqlSessionTemplate";
	
	@Bean(name = MYSQL_SESSION_FACTORY)
	public SqlSessionFactory sqlSessionFactoryForMySQL(
			@Qualifier(DataSourceConfigurations.MYSQL_DS)
			final DataSource mySqlDataSource,
			ApplicationContext applicationContext
			) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(mySqlDataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext
				.getResources("classpath*:mybatis/mysql/**/*.xml"));	
		sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean(name = MYSQL_SESSION_TEMPLATE)
	public SqlSessionTemplate sqlSessionTemplateForMySQL(
			@Qualifier(MYSQL_SESSION_FACTORY)
			final SqlSessionFactory mySqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(mySqlSessionFactory);
	}

}
