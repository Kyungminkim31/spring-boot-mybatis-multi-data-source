package org.gaval.poc.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfigurations {
	public static final String MYSQL_DS = "mysql_data_source";
	public static final String POSTGRES_DS = "postgres_data_source";
	
    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.postgres")
    public DataSourceProperties masterDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = POSTGRES_DS)
    @Primary
    @ConfigurationProperties("app.datasource.postgres.configuration")
    public HikariDataSource masterDataSource() {
        return masterDataSourceProperties()
            .initializeDataSourceBuilder()
            .type(HikariDataSource.class)
            .build();
    }

    @Bean
    @ConfigurationProperties("app.datasource.mysql")
    public DataSourceProperties slaveDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = MYSQL_DS)
    @ConfigurationProperties("app.datasource.mysql.configuration")
    public HikariDataSource slaveDataSource() {
        return slaveDataSourceProperties()
            .initializeDataSourceBuilder()
            .type(HikariDataSource.class)
            .build();
    }
}
